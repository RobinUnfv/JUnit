package com.company.inventory.services;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.model.Category;
import com.company.inventory.respnose.CategoryResponseRest;
import com.company.inventory.util.CategoriaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl service;

    @Mock
    ICategoryDao categoryDao;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void search() {
        when(categoryDao.findAll()).thenReturn(CategoriaUtil.listaCategorias);
        ResponseEntity<CategoryResponseRest> response = service.search();

        assertEquals(3, response.getBody().getCategoryResponse().getCategory().size());
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected HTTP status OK");
        assertNotNull(response.getBody(), "El cuerpo de la respuesta no debe ser nulo");

        verify(categoryDao, times(1) ).findAll();
    }

    void search_exception() {
        when(categoryDao.findAll()).thenThrow(new RuntimeException("Database error"));
        ResponseEntity<CategoryResponseRest> response = service.search();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "El código de estado HTTP debe ser INTERNAL_SERVER_ERROR");
        assertNotNull(response.getBody(), "El cuerpo de la respuesta no debe ser nulo");
        assertEquals("Respuesta nok", response.getBody().getMetadata().get(0).get("type") , "El tipo de respuesta debe ser 'Respuesta nok'");

        verify(categoryDao, times(1)).findAll();
    }

    /*

     */
    @Test
    void testSaveCategoryDaoReturnsNull() {
        Category category = new Category(4L, "Toys", "Children's toys");
        when(categoryDao.save(ArgumentMatchers.any())).thenReturn(null);

        ResponseEntity<CategoryResponseRest> response = service.save(category);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "El código de estado HTTP debe ser INTERNAL_SERVER_ERROR");
        assertNotNull(response.getBody(), "El cuerpo de la respuesta no debe ser nulo");
        assertEquals("Respuesta nok", response.getBody().getMetadata().get(0).get("type") , "El tipo de respuesta debe ser 'Respuesta nok'");

        verify(categoryDao, times(1)).save(ArgumentMatchers.any());

    }

}