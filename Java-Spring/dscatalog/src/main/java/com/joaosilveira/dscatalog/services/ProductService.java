package com.joaosilveira.dscatalog.services;

import com.joaosilveira.dscatalog.dtos.CategoryDTO;
import com.joaosilveira.dscatalog.dtos.ProductDTO;
import com.joaosilveira.dscatalog.entities.Category;
import com.joaosilveira.dscatalog.entities.Product;
import com.joaosilveira.dscatalog.projections.ProductProjection;
import com.joaosilveira.dscatalog.repositories.CategoryRepository;
import com.joaosilveira.dscatalog.repositories.ProductRepository;
import com.joaosilveira.dscatalog.services.exceptions.DatabaseException;
import com.joaosilveira.dscatalog.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.dscatalog.util.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(String name, String categoryId, Pageable pageable) {

        // CRIA UMA LISTA VAZIA DE LONG
        List<Long> categoryIds = Arrays.asList();

        // VERIFICA SE O QUE FOI PASSADO COMO "categoryId" é diferente de "0"
        // SE FOR DIFERENTE, ELE VAI GUARDAR NA LISTA TODOS OS NUMEROS PASSADOS
        if(!"0".equals(categoryId)) {
            categoryIds = Arrays.asList(categoryId.split(",")).stream().map(Long::parseLong).toList();
        }

        // FAZ A CONSULTA SQL
        Page<ProductProjection> page = productRepository.searchProducts(categoryIds, name, pageable);

        // PEGA SOMENTE OS IDS DOS PRODUTOS PASSADOS
        List<Long> productIds = page.map(ProductProjection::getId).toList();

        // FAZ OUTRA CONSULTA SQL AGORA PARA PEGAR OS ID JUNTAMENTE COM AS CATEGORIAS (PROBLEMA N+1 CONSULTAS RESOLVIDO)
        List<Product> entities = productRepository.searchProductsWithCategories(productIds);

        // O intuíto desse Utils.replace é pegar a ordenação que está na lista PAGE e montar uma nova lista de
        // produtos ordenadas com base na lista desordenada ENTITIES
        entities = (List<Product>) Utils.replace(page.getContent(), entities);

        // TRANSFORMA CADA PRODUCT EM PRODUCTDTO
        List<ProductDTO> dtos = entities.stream().map(ProductDTO::new).toList();

        // CRIA UMA PAGINA DE PRODUCTDTO
        Page<ProductDTO> pageDto = new PageImpl<>(dtos, page.getPageable(), page.getTotalElements());
        return pageDto;

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
