package com.devarch.agenticsdlc.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devarch.agenticsdlc.dto.CreateProductRequest;
import com.devarch.agenticsdlc.dto.ProductDto;
import com.devarch.agenticsdlc.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void shouldMapProductToDto() {
        Product product = Product.builder()
                .id(1L)
                .name("Widget Pro")
                .description("A premium widget")
                .price(BigDecimal.valueOf(29.99))
                .createdAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                .updatedAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                .build();

        ProductDto dto = productMapper.toDto(product);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("Widget Pro");
        assertThat(dto.getDescription()).isEqualTo("A premium widget");
        assertThat(dto.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(29.99));
        assertThat(dto.getCreatedAt()).isEqualTo(LocalDateTime.of(2025, 1, 1, 0, 0));
        assertThat(dto.getUpdatedAt()).isEqualTo(LocalDateTime.of(2025, 1, 1, 0, 0));
    }

    @Test
    void shouldMapCreateRequestToProduct() {
        CreateProductRequest request = CreateProductRequest.builder()
                .name("New Widget")
                .description("A new widget")
                .price(BigDecimal.valueOf(19.99))
                .build();

        Product product = productMapper.toModel(request);

        assertThat(product.getName()).isEqualTo("New Widget");
        assertThat(product.getDescription()).isEqualTo("A new widget");
        assertThat(product.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(19.99));
        assertThat(product.getId()).isNull();
        assertThat(product.getCreatedAt()).isNull();
        assertThat(product.getUpdatedAt()).isNull();
    }

    @Test
    void shouldUpdateProductFromRequest() {
        Product existing = Product.builder()
                .id(1L)
                .name("Old Name")
                .description("Old description")
                .price(BigDecimal.valueOf(10.00))
                .createdAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                .updatedAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                .build();

        CreateProductRequest updateRequest = CreateProductRequest.builder()
                .name("Updated Name")
                .description("Updated description")
                .price(BigDecimal.valueOf(49.99))
                .build();

        productMapper.updateModel(updateRequest, existing);

        assertThat(existing.getId()).isEqualTo(1L);
        assertThat(existing.getName()).isEqualTo("Updated Name");
        assertThat(existing.getDescription()).isEqualTo("Updated description");
        assertThat(existing.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(49.99));
        assertThat(existing.getCreatedAt()).isEqualTo(LocalDateTime.of(2025, 1, 1, 0, 0));
    }

    @Test
    void shouldHandleNullDescription() {
        CreateProductRequest request = CreateProductRequest.builder()
                .name("Widget")
                .price(BigDecimal.valueOf(9.99))
                .build();

        Product product = productMapper.toModel(request);

        assertThat(product.getName()).isEqualTo("Widget");
        assertThat(product.getDescription()).isNull();
    }
}
