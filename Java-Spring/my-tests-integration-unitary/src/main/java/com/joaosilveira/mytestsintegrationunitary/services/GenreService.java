package com.joaosilveira.mytestsintegrationunitary.services;

import com.joaosilveira.mytestsintegrationunitary.dtos.GenreDTO;
import com.joaosilveira.mytestsintegrationunitary.entities.Genre;
import com.joaosilveira.mytestsintegrationunitary.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;


    public List<Genre> findAllGenres() {
        return null;
    }

    public GenreDTO findGenreById(Long id) {
        return null;
    }

    public GenreDTO insertGenre(GenreDTO dto) {
        return null;
    }

    public GenreDTO update(Long id, GenreDTO dto) {
        return null;
    }

    public void deleteById(Long id) {

    }
}
