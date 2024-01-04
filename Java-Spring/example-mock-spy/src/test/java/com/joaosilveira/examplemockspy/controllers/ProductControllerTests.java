package com.joaosilveira.examplemockspy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaosilveira.examplemockspy.dto.ProductDTO;
import com.joaosilveira.examplemockspy.services.ProductService;
import com.joaosilveira.examplemockspy.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// testes unitários na camada de web
@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    // usado para transformar um objeto em json
    @Autowired
    private ObjectMapper objectMapper;

    // usado para realizar a requisição
    @Autowired
    private MockMvc mockMvc;

    private ProductDTO productDTO;

    @MockBean
    private ProductService productService;

    private Long existsId;
    private Long nonExistsId;

    @BeforeEach
    void setUp() throws Exception {
        productDTO = new ProductDTO(1L, "Carro", 20000.0);

        existsId = 1L;
        nonExistsId = 2L;

        // quando o insert for chamado passando qualquer argumento ele irá retornar um productDTO
        Mockito.when(productService.insert(any())).thenReturn(productDTO);

        // quando o update for chamado passando um id existente ele irá retornar o productDTO
        Mockito.when(productService.update(eq(existsId), any())).thenReturn(productDTO);

        // quando o update for chamado passando um id inexistente ele irá lançar uma excessão
        Mockito.when(productService.update(eq(nonExistsId), any())).thenThrow(ResourceNotFoundException.class);
    }


    @Test
    public void insertShouldReturnProductDTO() throws Exception {
        // transformando o objeto em json
        String jsonBody = objectMapper.writeValueAsString(productDTO);

        // realizando a requisição
        ResultActions result = mockMvc.perform(post("/products")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // vendo se os status retornado foi o 201 (isCreated)
        result.andExpect(status().isCreated());
        // vendo se no json retornado contém esses campos
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.price").exists());
    }


    @Test
    public void updateShouldReturnProductDTOWhenIdExists() throws Exception {
        // transformando o objeto em json
        String jsonBody = objectMapper.writeValueAsString(productDTO);

        // realizando a requisição passando a variável existsId como {id}
        ResultActions result = mockMvc.perform(put("/products/{id}", existsId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // vendo se os status retornado foi o 200 (isOk)
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.price").exists());
    }


    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", nonExistsId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }
}
