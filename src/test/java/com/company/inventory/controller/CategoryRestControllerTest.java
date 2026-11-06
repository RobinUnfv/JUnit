package com.company.inventory.controller;

import com.company.inventory.respnose.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;
import com.company.inventory.util.CategoriaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryRestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    CategoryRestController controller;

    @Mock
    ICategoryService service;

    @BeforeEach
    void setUp() {
      //MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testSearchCategories() throws Exception {
        CategoryResponseRest response = new CategoryResponseRest();
        response.getCategoryResponse().setCategory(CategoriaUtil.listaCategorias);
        response.setMetadata("Respuesta exitosa", "00", "Respuesta exitosa");

        when(service.search()).thenReturn(new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK));

        this.mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.categoryResponse").exists())
                .andExpect(jsonPath("$.categoryResponse.category[0].name").value("Electronics"))
                .andExpect(status().isOk());


    }

}