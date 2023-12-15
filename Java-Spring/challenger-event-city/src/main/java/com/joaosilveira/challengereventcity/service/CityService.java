package com.joaosilveira.challengereventcity.service;

import com.joaosilveira.challengereventcity.dto.CityDTO;
import com.joaosilveira.challengereventcity.entities.City;
import com.joaosilveira.challengereventcity.repositories.CityRepository;
import com.joaosilveira.challengereventcity.service.exceptions.DataBaseException;
import com.joaosilveira.challengereventcity.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;


    public List<CityDTO> findAll() {
        return cityRepository.findAllByName();
    }

    public CityDTO insert(CityDTO dto) {
        City city = new City();
        copyDtoToEntity(dto,city);
        city = cityRepository.save(city);
        return new CityDTO(city);
    }

    private void copyDtoToEntity(CityDTO dto, City entity) {
        entity.setName(dto.getName());
    }

    public void delete(Long id) {

        if (!cityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação integridade referencial");
        }
    }
}
