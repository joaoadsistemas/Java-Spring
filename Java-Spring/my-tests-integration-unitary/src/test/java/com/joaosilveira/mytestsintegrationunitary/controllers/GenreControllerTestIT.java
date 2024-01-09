package com.joaosilveira.mytestsintegrationunitary.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaosilveira.mytestsintegrationunitary.dtos.GenreNoFilmDTO;
import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.tests.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GenreControllerTestIT {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private GenreNoFilmDTO genreNoFilmDTO;

    @Autowired
    private TokenUtil tokenUtil;

    private Long existsId;
    private Long nonExistsId;

    private String username, password, bearerToken;

    @BeforeEach
    void setUp() throws Exception {

        genreNoFilmDTO = new GenreNoFilmDTO(1L, "Horror");

        existsId = 1L;
        // aqui esse id realmente nao pode existir no banco de dados
        nonExistsId = 1000L;

        // coloca o username e a senha de um usuário no banco de dados
        username = "joao@gmail.com";
        password = "12345678";

        bearerToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
    }
    @Test
    public void findAllGenresShouldReturnListGenreNoFilmDTO() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(get("/genres")
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$").isArray());
    }

    @Test
    public void findGenreByIdShouldReturnGenreNoFilmDTOWhenIdExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(get("/genres/{id}", existsId)
                .header("Authorization", "Bearer " + bearerToken)
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void findGenreByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(get("/genres/{id}", nonExistsId)
                .header("Authorization", "Bearer " + bearerToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    public void insertGenreShouldReturnGenreNoFilmDTOWhenValidData() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(post("/genres")
                .header("Authorization", "Bearer " + bearerToken)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void insertGenreShouldReturnUnprocessableEntityWhenInvalidData() throws Exception {

        genreNoFilmDTO = new GenreNoFilmDTO(1L, "");

        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(post("/genres")
                .header("Authorization", "Bearer " + bearerToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isUnprocessableEntity());
        result.andExpect(jsonPath("$.errors[1].message").value("Campo requerido"));

    }

    @Test
    public void updateShouldReturnGenreNoFilmDTOWhenIdExistsAndValidData() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(put("/genres/{id}", existsId)
                .header("Authorization", "Bearer " + bearerToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isOk());
    }

    @Test
    public void updateShouldReturnUnprocessableEntityWhenInvalidDataAndIdDoesNotExistsOrIdExists() throws Exception {

        genreNoFilmDTO = new GenreNoFilmDTO(1L, "");
        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(put("/genres/{id}", existsId)
                .header("Authorization", "Bearer " + bearerToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isUnprocessableEntity());
    }


    @Test
    public void deleteShouldReturnNoContentWhenIdExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(delete("/genres/{id}", existsId)
                .header("Authorization", "Bearer " + bearerToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(delete("/genres/{id}", nonExistsId)
                .header("Authorization", "Bearer " + bearerToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isNotFound());
    }
}

