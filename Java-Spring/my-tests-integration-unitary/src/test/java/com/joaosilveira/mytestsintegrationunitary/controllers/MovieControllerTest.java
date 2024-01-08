package com.joaosilveira.mytestsintegrationunitary.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.services.MovieService;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.mytestsintegrationunitary.tests.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// essa configuração de excludeAutoConfiguration tira todas as configurações de segurança, que bloqueariam cada
// requisição
@WebMvcTest(value = MovieController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class MovieControllerTest {

    // usado para transformar um objeto em json
    @Autowired
    private ObjectMapper objectMapper;

    // usado para realizar a requisição
    @Autowired
    private MockMvc mockMvc;

    private MovieDTO movieDTO;

    @MockBean
    private MovieService movieService;

    private Long existsId;
    private Long nonExistsId;

    @BeforeEach
    void setUp() throws Exception {

        movieDTO = new MovieDTO(1L, "Toy Store", "Lorem Ipsum");

        existsId = 1L;
        nonExistsId = 2L;

        // aqui diz que quando eu procurar um filme por id passando um id existente ele irá retornar um movieDTO
        Mockito.when(movieService.findMovieById(existsId)).thenReturn(movieDTO);
        // aqui diz que quando eu procurar um filme por id passando um id nao existente ele irá lançar uma exception
        Mockito.doThrow(ResourceNotFoundException.class).when(movieService).findMovieById(nonExistsId);
    }


    @Test
    public void findByIdShouldReturnMovieDTOWhenIdExists() throws Exception {

        // transformando o objeto em json
        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        // realizando a requisição
        ResultActions result = mockMvc.perform(get("/movies/{id}", existsId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        // verificando se a resposta da requisição foi um 200 (isOk)
        result.andExpect(status().isOk());
        // verificando se o id passado existe e se o valor dele é igual o do DTO que passei
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.id").value(1L));

    }

}
