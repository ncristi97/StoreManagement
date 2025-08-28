package org.example.controller;

import org.example.domain.Product;
import org.example.service.ProductService;
import org.example.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenGetAllProducts_thenReturnsList() throws Exception {
        List<Product> products = List.of(
                new Product("Laptop", 1200),
                new Product("Phone", 800)
        );
        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/api/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[1].price").value(800));

        verify(productService, times(1)).findAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenGetProductById_thenReturnsProduct() throws Exception {
        Product product = new Product("Laptop", 1200);
        when(productService.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.price").value(1200));

        verify(productService, times(1)).findById(1L);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenProductNotFound_thenReturnsError() throws Exception {
        when(productService.findById(999L))
                .thenThrow(new NotFoundException("Product not found"));

        mockMvc.perform(get("/api/products/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Product not found"));
    }
}
