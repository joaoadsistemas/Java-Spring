package com.joaosilveira.mytestsintegrationunitary.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaosilveira.mytestsintegrationunitary.dtos.GenreNoFilmDTO;
import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.services.GenreService;
import com.joaosilveira.mytestsintegrationunitary.services.MovieService;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
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

import java.util.List;

@WebMvcTest(value = GenreController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class GenreControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private GenreNoFilmDTO genreNoFilmDTO;

    @MockBean
    private GenreService genreService;

    private Long existsId;
    private Long nonExistsId;


    @BeforeEach
    void setUp() throws Exception {

        genreNoFilmDTO = new GenreNoFilmDTO(1L, "Horror");

        existsId = 1L;
        nonExistsId = 2L;


        Mockito.when(genreService.findAllGenres()).thenReturn(List.of(genreNoFilmDTO));

        Mockito.when(genreService.findGenreById(existsId)).thenReturn(genreNoFilmDTO);
        Mockito.doThrow(ResourceNotFoundException.class).when(genreService).findGenreById(nonExistsId);

        Mockito.when(genreService.insertGenre(any())).thenReturn(genreNoFilmDTO);

        Mockito.when(genreService.update(existsId, genreNoFilmDTO)).thenReturn(genreNoFilmDTO);

        Mockito.doNothing().when(genreService).deleteById(existsId);
        Mockito.doThrow(ResourceNotFoundException.class).when(genreService).deleteById(nonExistsId);
    }

    @Test
    public void findAllGenresShouldReturnListGenreNoFilmDTO() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(genreNoFilmDTO);

        ResultActions result = mockMvc.perform(get("/genres")
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
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );

        result.andExpect(status().isNotFound());
    }
}
