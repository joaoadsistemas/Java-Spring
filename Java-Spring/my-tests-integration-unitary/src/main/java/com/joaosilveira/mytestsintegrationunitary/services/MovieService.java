package com.joaosilveira.mytestsintegrationunitary.services;

import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.entities.Movie;
import com.joaosilveira.mytestsintegrationunitary.projections.MovieProjection;
import com.joaosilveira.mytestsintegrationunitary.repositories.MovieRepository;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.DatabaseException;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> findAllMoviesPage(Pageable pageable) {
        Page<MovieProjection> movieProjections = movieRepository.findPageable(pageable);
        Page<MovieDTO> result = movieProjections.map(MovieDTO::new);
        return result;
    }

    @Transactional(readOnly = true)
    public MovieDTO findMovieById(Long id) {

        Optional<Movie> movieOptional = movieRepository.findById(id);

        Movie result = movieOptional.orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );

        return new MovieDTO(result);
    }

    @Transactional
    public MovieDTO insertMovie(MovieDTO dto) {
        Movie entity = new Movie();
        copyDtoToEntity(dto, entity);
        entity = movieRepository.save(entity);
        return new MovieDTO(entity);
    }

    @Transactional
    public MovieDTO updateMovie(Long id, MovieDTO dto) {
        try {
            Movie entity = movieRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = movieRepository.save(entity);
            return new MovieDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id inválido");
        }
    }

    @Transactional
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id inválido");
        }
        try {
            movieRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade referencial");
        }
    }

    private void copyDtoToEntity(MovieDTO dto, Movie entity) {
        entity.setId(dto.getId());
        entity.setMovieName(dto.getMovieName());
        entity.setDescription(dto.getDescription());
    }
}
