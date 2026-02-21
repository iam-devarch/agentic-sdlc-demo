package com.devarch.agenticsdlc.controller;

import java.util.List;

import com.devarch.agenticsdlc.dto.CreateProductRequest;
import com.devarch.agenticsdlc.dto.ProductDto;
import com.devarch.agenticsdlc.mapper.ProductMapper;
import com.devarch.agenticsdlc.model.Product;
import com.devarch.agenticsdlc.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for Product Catalog API.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    /**
     * List all products with pagination.
     */
    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.debug("GET /api/v1/products - page: {}, size: {}", page, size);
        List<ProductDto> products = productService.findAll(page, size).stream()
                .map(productMapper::toDto)
                .toList();
        return ResponseEntity.ok(products);
    }

    /**
     * Get a product by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        log.debug("GET /api/v1/products/{}", id);
        Product product = productService.findById(id);
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    /**
     * Create a new product.
     */
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody CreateProductRequest request) {
        log.debug("POST /api/v1/products - name: {}", request.getName());
        Product created = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toDto(created));
    }

    /**
     * Update an existing product.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
            @Valid @RequestBody CreateProductRequest request) {
        log.debug("PUT /api/v1/products/{}", id);
        Product updated = productService.update(id, request);
        return ResponseEntity.ok(productMapper.toDto(updated));
    }

    /**
     * Delete a product.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("DELETE /api/v1/products/{}", id);
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
