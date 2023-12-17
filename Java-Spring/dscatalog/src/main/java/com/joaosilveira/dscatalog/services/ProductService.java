package com.joaosilveira.dscatalog.services;

import com.joaosilveira.dscatalog.dtos.CategoryDTO;
import com.joaosilveira.dscatalog.dtos.ProductDTO;
import com.joaosilveira.dscatalog.entities.Category;
import com.joaosilveira.dscatalog.entities.Product;
import com.joaosilveira.dscatalog.repositories.CategoryRepository;
import com.joaosilveira.dscatalog.repositories.ProductRepository;
import com.joaosilveira.dscatalog.services.exceptions.DatabaseException;
import com.joaosilveira.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public Page<ProductDTO> findPageable(String name, Pageable pageable) {
        Page<ProductDTO> page = productRepository.findPageable(name, pageable);
        return page;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new ProductDTO(product);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }

    }

    @Transactional
    public ProductDTO update(ProductDTO dto, Long id) {
        try {
            Product entity = productRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }


    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }





    public void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());

        // SALVAR UMA CATEGORIA JUNTAMENTE COM A CRIAÇÃO DE UM NOVO PRODUTO
        // VALIDA SE O ID DE CATEGORIA PASSADO EXISTE, SE EXISTIR APENAS PEGA ELE E RETORNA
        // SE NÃO CRIA UMA NOVA CATEGORIA COM BASE NESSA QUE FOI PASSADA
        for (CategoryDTO catDto: dto.getCategories()) {
            Category category = new Category();
            if (categoryRepository.existsById(catDto.getId())) {
                category = categoryRepository.getReferenceById(catDto.getId());
                entity.getCategories().add(category);
            }
            dtoToCategory(catDto, category);
            categoryRepository.save(category);
            entity.getCategories().add(category);
        }

    }

    public void dtoToCategory(CategoryDTO dto, Category category) {
        category.setName(dto.getName());
    }
}
