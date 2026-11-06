package com.company.inventory.util;

import com.company.inventory.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriaUtil {

    public static final List<Category> listaCategorias = List.of(
            new Category(1L, "Electronics", "Devices and gadgets"),
            new Category(2L, "Clothing", "Apparel and accessories"),
            new Category(3L, "Books", "Literature and educational materials")
    );


}
