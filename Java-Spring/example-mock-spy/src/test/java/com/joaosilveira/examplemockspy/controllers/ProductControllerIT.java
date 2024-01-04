package com.joaosilveira.examplemockspy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaosilveira.examplemockspy.dto.ProductDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// testes de integração no controller
// a diferença entre ele o teste unitário do controller, é que esse não precisamos mockar os valores do ProductService
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerIT {

    // usado para realizar requisições
    @Autowired
    private MockMvc mockMvc;

    // usado para transformar um objeto em json
    @Autowired
    private ObjectMapper objectMapper;

    private ProductDTO productDTO;

    private long existingId;
    private long nonExistingId;

    @BeforeEach
    void setUp() throws Exception {
        productDTO = new ProductDTO(1L, "Carro", 20000.0);
        existingId = 1L;
        nonExistingId = 1000L;
    }

    // MESMAS EXPLICAÇÕES DO ProductControllerTests
    @Test
    public void insertShouldReturnProductDTO() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(post("/products")
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.name").value(productDTO.getName()));
    }

    // MESMAS EXPLICAÇÕES DO ProductControllerTests
    @Test
    public void updateShouldReturnProductDTOWhenIdExists() throws Exception {

        productDTO.setName("Moto");

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", existingId)
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value("Moto"));
    }

    // MESMAS EXPLICAÇÕES DO ProductControllerTests
    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", nonExistingId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isNotFound());

    }
}



