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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// testes de integração no controller
// a diferença entre ele o teste unitário do controller, é que esse não precisamos mockar os valores do MovieService
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MovieControllerTestIT {

    // usado para transformar um objeto em json
    @Autowired
    private ObjectMapper objectMapper;

    // usado para realizar a requisição
    @Autowired
    private MockMvc mockMvc;

    private MovieDTO movieDTO;

    @Autowired
    private TokenUtil tokenUtil;

    private Long existsId;
    private Long nonExistsId;

    private String username, password, bearerToken;

    @BeforeEach
    void setUp() throws Exception {

        movieDTO = new MovieDTO(1L, "Toy Store", "Lorem Ipsum");

        existsId = 1L;
        // aqui esse id realmente nao pode existir no banco de dados
        nonExistsId = 1000L;

        // coloca o username e a senha de um usuário no banco de dados
        username = "joao@gmail.com";
        password = "12345678";

        bearerToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
    }


    @Test
    public void findByIdShouldReturnMovieDTOWhenIdExists() throws Exception {
        // transformando o objeto em json
        String jsonBody = objectMapper.writeValueAsString(movieDTO);

        // realizando a requisição
        ResultActions result = mockMvc.perform(get("/movies/{id}", existsId)
                .header("Authorization", "Bearer " + bearerToken)
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
                .header("Authorization", "Bearer " + bearerToken)
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
                .header("Authorization", "Bearer " + bearerToken)
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
                .header("Authorization", "Bearer " + bearerToken)
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
                .header("Authorization", "Bearer " + bearerToken)
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
                .header("Authorization", "Bearer " + bearerToken)
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
                .header("Authorization", "Bearer " + bearerToken)
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
                .header("Authorization", "Bearer " + bearerToken)
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
                .header("Authorization", "Bearer " + bearerToken)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isNotFound());
    }

}
