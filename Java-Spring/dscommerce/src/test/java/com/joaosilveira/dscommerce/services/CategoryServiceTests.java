package com.joaosilveira.dscommerce.services;

import com.joaosilveira.dscommerce.dto.CategoryDTO;
import com.joaosilveira.dscommerce.entities.Category;
import com.joaosilveira.dscommerce.repositories.CategoryRepository;
import com.joaosilveira.dscommerce.tests.CategoryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

// não quer que seja carregado o contexto da aplicação
@ExtendWith(SpringExtension.class)
public class CategoryServiceTests {

    // ja faz a implementação de todas as dependencias necessarias
    @InjectMocks
    private CategoryService categoryService;

    // Classe a ser mockada
    @Mock
    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> list;


    @BeforeEach
    void setUp() throws Exception {

        // chama a função fábrica que criei para instanciar uma nova categoria
        category = CategoryFactory.createCategory();

        list = new ArrayList<>();
        // adiciona a categoria a lista de categorias
        list.add(category);

        // quando chamar o categoryRepository.findAll ele deve retornar uma lista de categorias
        Mockito.when(categoryRepository.findAll()).thenReturn(list);
    }

    @Test
    public void findAllShouldReturnListCategoryDTO() {

        List<CategoryDTO> result = categoryService.findAll();

        // verifica se o tamanho da lista é igual a 1
        Assertions.assertEquals(result.size(), 1);
        // verifica se o id da categoria na posição zero é igual ao id da categoria que passei
        Assertions.assertEquals(result.get(0).getId(), category.getId());
        // verifica se o name da categoria na posição zero é igual ao name da categoria que passei
        Assertions.assertEquals(result.get(0).getName(), category.getName());
    }

}
