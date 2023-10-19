package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    ParkingSpotService parkingSpotService;

    @PostMapping
    public ResponseEntity saveParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDTO.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use");
        }
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDTO.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use");
        }
        if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDTO.getApartment(), parkingSpotDTO.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Apartment and Block is already in use");
        }
        parkingSpotService.existsByParkinSpotNumber(parkingSpotDTO.getParkingSpotNumber());
        parkingSpotService.existsByApartmentAndBlock(parkingSpotDTO.getApartment(), parkingSpotDTO.getBlock());
        return parkingSpotService.save(parkingSpotDTO);
    }

    @GetMapping("/")
    public ResponseEntity<Page<ParkingSpotModel>> listAll(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC)Pageable pageable) {
        return parkingSpotService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotModel> findById(@PathVariable(value = "id") UUID id) {
        return parkingSpotService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID id) {
        return parkingSpotService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
        return parkingSpotService.updateById(id, parkingSpotDTO);

    }

}
