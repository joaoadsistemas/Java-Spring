package com.joaosilveira.dscommerce.services;

import com.joaosilveira.dscommerce.dto.ProductDTO;
import com.joaosilveira.dscommerce.entities.Product;
import com.joaosilveira.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // SERVICE RETORNA SOMENTE DTO
    // Transaction do Spring, nao do JAKARTA
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;
    }

    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> product = repository.findAll(pageable);
        return product.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }




    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }


}
