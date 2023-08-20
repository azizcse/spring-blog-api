package com.example.simplecrud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import com.example.simplecrud.controller.*;
import com.example.simplecrud.repository.*;
import com.example.simplecrud.model.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(new ProductController(productRepository)).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(10.99));
        product.setCategory("Test Category");

        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(product.getName()))
                .andExpect(jsonPath("$[0].description").value(product.getDescription()))
                .andExpect(jsonPath("$[0].price").value(product.getPrice()))
                .andExpect(jsonPath("$[0].category").value(product.getCategory()));
    }

}

