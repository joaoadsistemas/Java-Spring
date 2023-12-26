package com.joaosilveira.challengeauthsecurity.services;

import com.joaosilveira.challengeauthsecurity.dto.CityDTO;
import com.joaosilveira.challengeauthsecurity.entities.City;
import com.joaosilveira.challengeauthsecurity.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;


    @Transactional
    public CityDTO insert(CityDTO dto) {
        City city = new City();
        copyDtoToEntity(dto, city);
        city = cityRepository.save(city);
        return new CityDTO(city);
    }

    @Transactional
    public List<CityDTO> findAll() {
        List<CityDTO> result = cityRepository.findAllByOrderByNomeAsc();
        return result;
    }

    private void copyDtoToEntity(CityDTO dto, City city) {
        city.setId(dto.getId());
        city.setName(dto.getName());
    }

}
