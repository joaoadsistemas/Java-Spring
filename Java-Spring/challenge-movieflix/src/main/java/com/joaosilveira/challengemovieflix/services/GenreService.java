package com.joaosilveira.challengemovieflix.services;

import com.joaosilveira.challengemovieflix.dto.GenreDTO;
import com.joaosilveira.challengemovieflix.entities.Genre;
import com.joaosilveira.challengemovieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public List<GenreDTO> findAll() {
        List<Genre> list = genreRepository.findAll();
        return list.stream().map(GenreDTO::new).toList();
    }
}
