package com.joaosilveira.dscommerce.tests;

import com.joaosilveira.dscommerce.entities.Category;


// CLASSE FABRICA PARA A INSTANCIAÇÃO DE NOVAS CATEGORYS
public class CategoryFactory {

    public static Category createCategory() {
        return new Category(1L, "Games");
    }

    public static Category createCategory(Long id, String name) {
        return new Category(id, name);
    }
}
