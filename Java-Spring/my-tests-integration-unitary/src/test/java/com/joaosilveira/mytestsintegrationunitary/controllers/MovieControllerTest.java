package com.joaosilveira.mytestsintegrationunitary.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.services.MovieService;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.mytestsintegrationunitary.tests.TokenUtil;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

        // quando chamar o movieService.insertMovie passando qualquer argumento, ele irá retornar um movieDTO
        Mockito.when(movieService.insertMovie(any())).thenReturn(movieDTO);

        // quando chamar o movieService.updateMovie passando um Id existente e um movieDTO ele retorna um movieDTO
        Mockito.when(movieService.updateMovie(existsId, movieDTO)).thenReturn(movieDTO);
        // quando chamar o movieService.updateMovie passando um Id inexistente e um movieDTO ele lança uma exception
        Mockito.when(movieService.updateMovie(nonExistsId, movieDTO)).thenThrow(ResourceNotFoundException.class);

        // quando chamar o movieService.deleteMovie passando um Id Inexistente deve lançar uma exception
        Mockito.doThrow(ResourceNotFoundException.class).when(movieService).deleteMovie(nonExistsId);
    }


    @Test
    public void findByIdShouldReturnMovieDTOWhenIdExists() throws Exception {

        // transformando o objeto em json
        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        // realizando a requisição também passado o id existente na URL
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

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        ResultActions result = mockMvc.perform(get("/movies/{id}", nonExistsId)
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isNotFound());
    }

    @Test
    public void insertShouldReturnMovieDTOWhenValidData() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        ResultActions result = mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void insertShouldReturnUnprocessableEntityWhenInvalidData() throws Exception {

        movieDTO = new MovieDTO(1L, "", "");
        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        ResultActions result = mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isUnprocessableEntity());
        result.andExpect(jsonPath("$.errors[0].message").value("Campo requerido"));}

    @Test
    public void updateShouldReturnMovieDTOWhenIdExistsAndValidData() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        ResultActions result = mockMvc.perform(put("/movies/{id}", existsId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
    }

    @Test
    public void updateShouldReturnUnprocessableEntityWhenInvalidDataAndExistsId() throws Exception {

        movieDTO = new MovieDTO(1L, "", "");

        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        ResultActions result = mockMvc.perform(put("/movies/{id}", existsId)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isUnprocessableEntity());
        result.andExpect(jsonPath("$.errors[0].message").value("Campo requerido"));

    }

    @Test
    public void updateShouldReturnUnprocessableEntityWhenInvalidDataAndDoesNotExistsId() throws Exception {

        movieDTO = new MovieDTO(1L, "", "");

        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        ResultActions result = mockMvc.perform(put("/movies/{id}", nonExistsId)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isUnprocessableEntity());
        result.andExpect(jsonPath("$.errors[0].message").value("Campo requerido"));

    }

    @Test
    public void deleteShouldReturnNothingWhenIdExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        ResultActions result = mockMvc.perform(delete("/movies/{id}", existsId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isNoContent());
        result.andExpect(jsonPath("$.id").doesNotExist());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        ResultActions result = mockMvc.perform(delete("/movies/{id}", nonExistsId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isNotFound());
    }
}
