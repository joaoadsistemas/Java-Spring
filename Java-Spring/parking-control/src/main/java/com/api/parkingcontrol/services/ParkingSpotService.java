package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;


    public ResponseEntity<ParkingSpotModel> save(ParkingSpotDTO parkingSpotDTO) {
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        parkingSpotRepository.save(parkingSpotModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotModel);
    }


    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);

    }

    public boolean existsByParkinSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
       return parkingSpotRepository.existsByApartmentAndBlock(apartment,block);
    }

    public ResponseEntity<Page<ParkingSpotModel>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotRepository.findAll(pageable));
    }

    public ResponseEntity findById(UUID id) {
        System.out.println("ID -----------    " + id);
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotRepository.findById(id);
        if (!parkingSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel.get());
    }

    @Transactional
    public ResponseEntity<Object> deleteById(UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotRepository.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }
        parkingSpotRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot Deleted sucessfuly");

    }

    @Transactional
    public ResponseEntity<Object> updateById(UUID id, ParkingSpotDTO parkingSpotDTO) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotRepository.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }

        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotRepository.save(parkingSpotModel));


    }
}
