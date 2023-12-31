package com.joaosilveira.challengemovieflix.services;


import com.joaosilveira.challengemovieflix.dto.MovieDetailsDTO;
import com.joaosilveira.challengemovieflix.entities.Movie;
import com.joaosilveira.challengemovieflix.projections.GenreProjection;
import com.joaosilveira.challengemovieflix.repositories.MovieRepository;
import com.joaosilveira.challengemovieflix.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.challengemovieflix.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        Movie movie = movieOptional.orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new MovieDetailsDTO(movie);
    }

    public Page<MovieDetailsDTO> findAllPaged(String title, String genreId, Pageable pageable) {

        // CRIA UMA LISTA VAZIA DE LONG
        List<Long> genreIds = Arrays.asList();

        // VERIFICA SE O QUE FOI PASSADO COMO "genreIds" é diferente de "0"
        // SE FOR DIFERENTE, ELE VAI GUARDAR NA LISTA TODOS OS NUMEROS PASSADOS
        if(!"0".equals(genreId)) {
            genreIds = Arrays.asList(genreId.split(",")).stream().map(Long::parseLong).toList();
        }

        // FAZ A CONSULTA SQL
        Page<GenreProjection> page = movieRepository.searchMovies(genreIds, title, pageable);

        // PEGA SOMENTE OS IDS DOS PRODUTOS PASSADOS
        List<Long> moviesIds = page.map(GenreProjection::getId).toList();

        // FAZ OUTRA CONSULTA SQL AGORA PARA PEGAR OS ID JUNTAMENTE COM AS CATEGORIAS (PROBLEMA N+1 CONSULTAS RESOLVIDO)
        List<Movie> entities = movieRepository.searchMoviesWithGenres(moviesIds);

        // O intuíto desse Utils.replace é pegar a ordenação que está na lista PAGE e montar uma nova lista de
        // produtos ordenadas com base na lista desordenada ENTITIES
        entities = (List<Movie>) Utils.replace(page.getContent(), entities);

        // TRANSFORMA CADA PRODUCT EM PRODUCTDTO
        List<MovieDetailsDTO> dtos = entities.stream().map(MovieDetailsDTO::new).toList();

        // CRIA UMA PAGINA DE PRODUCTDTO
        Page<MovieDetailsDTO> pageDto = new PageImpl<>(dtos, page.getPageable(), page.getTotalElements());
        return pageDto;
    }
}
