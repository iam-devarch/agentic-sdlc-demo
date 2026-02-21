package com.devarch.agenticsdlc.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.devarch.agenticsdlc.dto.CreateProductRequest;
import com.devarch.agenticsdlc.dto.ProductDto;
import com.devarch.agenticsdlc.exception.ProductNotFoundException;
import com.devarch.agenticsdlc.mapper.ProductMapper;
import com.devarch.agenticsdlc.model.Product;
import com.devarch.agenticsdlc.service.ProductService;
import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private ProductService productService;

        @MockitoBean
        private ProductMapper productMapper;

        @Autowired
        private ObjectMapper objectMapper;

        private Product sampleProduct;
        private ProductDto sampleProductDto;
        private CreateProductRequest sampleRequest;

        @BeforeEach
        void setUp() {
                sampleProduct = Product.builder()
                                .id(1L)
                                .name("Widget Pro")
                                .description("A premium widget")
                                .price(BigDecimal.valueOf(29.99))
                                .createdAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                                .updatedAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                                .build();

                sampleProductDto = ProductDto.builder()
                                .id(1L)
                                .name("Widget Pro")
                                .description("A premium widget")
                                .price(BigDecimal.valueOf(29.99))
                                .createdAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                                .updatedAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                                .build();

                sampleRequest = CreateProductRequest.builder()
                                .name("Widget Pro")
                                .description("A premium widget")
                                .price(BigDecimal.valueOf(29.99))
                                .build();
        }

        @Test
        void shouldListProducts() throws Exception {
                when(productService.findAll(0, 20)).thenReturn(List.of(sampleProduct));
                when(productMapper.toDto(sampleProduct)).thenReturn(sampleProductDto);

                mockMvc.perform(get("/api/v1/products"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].name").value("Widget Pro"));
        }

        @Test
        void shouldReturnEmptyListWhenNoProducts() throws Exception {
                when(productService.findAll(0, 20)).thenReturn(List.of());

                mockMvc.perform(get("/api/v1/products"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());
        }

        @Test
        void shouldGetProductById() throws Exception {
                when(productService.findById(1L)).thenReturn(sampleProduct);
                when(productMapper.toDto(sampleProduct)).thenReturn(sampleProductDto);

                mockMvc.perform(get("/api/v1/products/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("Widget Pro"))
                                .andExpect(jsonPath("$.price").value(29.99));
        }

        @Test
        void shouldReturn404WhenProductNotFound() throws Exception {
                when(productService.findById(99L)).thenThrow(new ProductNotFoundException(99L));

                mockMvc.perform(get("/api/v1/products/99"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.message").value("Product not found with id: 99"));
        }

        @Test
        void shouldCreateProduct() throws Exception {
                when(productService.create(any(CreateProductRequest.class))).thenReturn(sampleProduct);
                when(productMapper.toDto(sampleProduct)).thenReturn(sampleProductDto);

                mockMvc.perform(post("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sampleRequest)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("Widget Pro"));
        }

        @Test
        void shouldReturn400WhenNameIsBlank() throws Exception {
                CreateProductRequest invalidRequest = CreateProductRequest.builder()
                                .name("")
                                .price(BigDecimal.valueOf(29.99))
                                .build();

                mockMvc.perform(post("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400WhenPriceIsNegative() throws Exception {
                CreateProductRequest invalidRequest = CreateProductRequest.builder()
                                .name("Test Product")
                                .price(BigDecimal.valueOf(-10))
                                .build();

                mockMvc.perform(post("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldUpdateProduct() throws Exception {
                when(productService.update(eq(1L), any(CreateProductRequest.class))).thenReturn(sampleProduct);
                when(productMapper.toDto(sampleProduct)).thenReturn(sampleProductDto);

                mockMvc.perform(put("/api/v1/products/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sampleRequest)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.name").value("Widget Pro"));
        }

        @Test
        void shouldReturn404WhenUpdatingNonExistentProduct() throws Exception {
                when(productService.update(eq(99L), any(CreateProductRequest.class)))
                                .thenThrow(new ProductNotFoundException(99L));

                mockMvc.perform(put("/api/v1/products/99")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sampleRequest)))
                                .andExpect(status().isNotFound());
        }

        @Test
        void shouldDeleteProduct() throws Exception {
                doNothing().when(productService).delete(1L);

                mockMvc.perform(delete("/api/v1/products/1"))
                                .andExpect(status().isNoContent());
        }

        @Test
        void shouldReturn404WhenDeletingNonExistentProduct() throws Exception {
                doThrow(new ProductNotFoundException(99L)).when(productService).delete(99L);

                mockMvc.perform(delete("/api/v1/products/99"))
                                .andExpect(status().isNotFound());
        }
}
