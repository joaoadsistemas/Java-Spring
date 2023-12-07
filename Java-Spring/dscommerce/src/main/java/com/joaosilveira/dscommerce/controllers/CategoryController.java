package com.joaosilveira.dscommerce.controllers;

import com.joaosilveira.dscommerce.dto.CategoryDTO;
import com.joaosilveira.dscommerce.dto.OrderDTO;
import com.joaosilveira.dscommerce.services.CategoryService;
import com.joaosilveira.dscommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }



}
