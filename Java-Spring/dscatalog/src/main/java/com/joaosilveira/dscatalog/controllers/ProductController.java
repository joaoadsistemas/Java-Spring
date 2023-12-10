package com.joaosilveira.dscatalog.controllers;

import com.joaosilveira.dscatalog.dtos.ProductDTO;
import com.joaosilveira.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPageable(@RequestParam(name = "name", defaultValue = "") String name,
                                                         Pageable pageable) {
        return ResponseEntity.ok(productService.findPageable(name, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        dto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}