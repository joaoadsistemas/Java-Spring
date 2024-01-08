package com.joaosilveira.mytestsintegrationunitary.services;

import com.joaosilveira.mytestsintegrationunitary.dtos.GenreDTO;
import com.joaosilveira.mytestsintegrationunitary.dtos.GenreNoFilmDTO;
import com.joaosilveira.mytestsintegrationunitary.entities.Genre;
import com.joaosilveira.mytestsintegrationunitary.repositories.GenreRepository;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.DatabaseException;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;


    @Transactional(readOnly = true)
    public List<GenreNoFilmDTO> findAllGenres() {
        List<Genre> result = genreRepository.findAll();
        return result.stream().map(GenreNoFilmDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GenreNoFilmDTO findGenreById(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        Genre genre = genreOptional.orElseThrow(() -> {
            throw new ResourceNotFoundException("Recurso não encontrado");
        });
        return new GenreNoFilmDTO(genre);
    }

    @Transactional
    public GenreNoFilmDTO insertGenre(GenreNoFilmDTO dto) {
        Genre entity = new Genre();
        copyDtoToEntity(dto, entity);
        entity = genreRepository.save(entity);
        return new GenreNoFilmDTO(entity);
    }

    @Transactional
    public GenreNoFilmDTO update(Long id, GenreNoFilmDTO dto) {
        try {
            Genre genre = genreRepository.getReferenceById(id);
            copyDtoToEntity(dto, genre);
            genre = genreRepository.save(genre);
            return new GenreNoFilmDTO(genre);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    // necessita desse propagation para que ele consiga pegar o erro de integridade referencial
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {

        if (!genreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

        try {
            genreRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade referencial");
        }
    }

    private void copyDtoToEntity(GenreNoFilmDTO dto, Genre entity) {
        entity.setGenreName(dto.getGenreName());
    }
}
