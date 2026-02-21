package com.devarch.agenticsdlc.service;

import java.math.BigDecimal;
import java.util.List;

import com.devarch.agenticsdlc.dto.CreateProductRequest;
import com.devarch.agenticsdlc.exception.ProductNotFoundException;
import com.devarch.agenticsdlc.mapper.ProductMapper;
import com.devarch.agenticsdlc.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private CreateProductRequest sampleRequest;

    @BeforeEach
    void setUp() {
        sampleRequest = CreateProductRequest.builder()
                .name("Widget Pro")
                .description("A premium widget")
                .price(BigDecimal.valueOf(29.99))
                .build();
    }

    @Test
    void shouldCreateProduct() {
        Product mapped = Product.builder()
                .name("Widget Pro")
                .description("A premium widget")
                .price(BigDecimal.valueOf(29.99))
                .build();
        when(productMapper.toModel(sampleRequest)).thenReturn(mapped);

        Product result = productService.create(sampleRequest);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("Widget Pro");
        assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(29.99));
        assertThat(result.getCreatedAt()).isNotNull();
        assertThat(result.getUpdatedAt()).isNotNull();
    }

    @Test
    void shouldFindProductById() {
        Product mapped = Product.builder()
                .name("Widget Pro")
                .description("A premium widget")
                .price(BigDecimal.valueOf(29.99))
                .build();
        when(productMapper.toModel(sampleRequest)).thenReturn(mapped);

        Product created = productService.create(sampleRequest);
        Product found = productService.findById(created.getId());

        assertThat(found.getName()).isEqualTo("Widget Pro");
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        assertThatThrownBy(() -> productService.findById(999L))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    void shouldFindAllProducts() {
        Product mapped1 = Product.builder().name("Product A").price(BigDecimal.TEN).build();
        Product mapped2 = Product.builder().name("Product B").price(BigDecimal.ONE).build();

        when(productMapper.toModel(any(CreateProductRequest.class)))
                .thenReturn(mapped1, mapped2);

        productService.create(CreateProductRequest.builder()
                .name("Product A").price(BigDecimal.TEN).build());
        productService.create(CreateProductRequest.builder()
                .name("Product B").price(BigDecimal.ONE).build());

        List<Product> products = productService.findAll(0, 20);

        assertThat(products).hasSize(2);
    }

    @Test
    void shouldReturnEmptyListWhenNoProducts() {
        List<Product> products = productService.findAll(0, 20);
        assertThat(products).isEmpty();
    }

    @Test
    void shouldPaginateResults() {
        for (int i = 0; i < 5; i++) {
            Product mapped = Product.builder()
                    .name("Product " + i)
                    .price(BigDecimal.TEN)
                    .build();
            when(productMapper.toModel(any(CreateProductRequest.class))).thenReturn(mapped);
            productService.create(CreateProductRequest.builder()
                    .name("Product " + i).price(BigDecimal.TEN).build());
        }

        List<Product> page0 = productService.findAll(0, 2);
        List<Product> page1 = productService.findAll(1, 2);

        assertThat(page0).hasSize(2);
        assertThat(page1).hasSize(2);
    }

    @Test
    void shouldUpdateProduct() {
        Product mapped = Product.builder()
                .name("Widget Pro")
                .description("A premium widget")
                .price(BigDecimal.valueOf(29.99))
                .build();
        when(productMapper.toModel(sampleRequest)).thenReturn(mapped);

        Product created = productService.create(sampleRequest);

        CreateProductRequest updateRequest = CreateProductRequest.builder()
                .name("Widget Pro V2")
                .description("Updated description")
                .price(BigDecimal.valueOf(39.99))
                .build();

        doAnswer(invocation -> {
            CreateProductRequest req = invocation.getArgument(0);
            Product target = invocation.getArgument(1);
            target.setName(req.getName());
            target.setDescription(req.getDescription());
            target.setPrice(req.getPrice());
            return null;
        }).when(productMapper).updateModel(any(CreateProductRequest.class), any(Product.class));

        Product updated = productService.update(created.getId(), updateRequest);

        assertThat(updated.getName()).isEqualTo("Widget Pro V2");
        assertThat(updated.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(39.99));
    }

    @Test
    void shouldThrowWhenUpdatingNonExistentProduct() {
        assertThatThrownBy(() -> productService.update(999L, sampleRequest))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void shouldDeleteProduct() {
        Product mapped = Product.builder()
                .name("Widget Pro")
                .price(BigDecimal.valueOf(29.99))
                .build();
        when(productMapper.toModel(sampleRequest)).thenReturn(mapped);

        Product created = productService.create(sampleRequest);
        productService.delete(created.getId());

        assertThatThrownBy(() -> productService.findById(created.getId()))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void shouldThrowWhenDeletingNonExistentProduct() {
        assertThatThrownBy(() -> productService.delete(999L))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
