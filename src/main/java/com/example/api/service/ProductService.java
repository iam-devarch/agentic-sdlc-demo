package com.example.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.example.api.dto.CreateProductRequest;
import com.example.api.exception.ProductNotFoundException;
import com.example.api.mapper.ProductMapper;
import com.example.api.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service layer for Product business logic.
 * Uses an in-memory store for demo purposes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Retrieve all products with simple pagination.
     *
     * @param page page number (0-based)
     * @param size page size
     * @return paginated list of products
     */
    public List<Product> findAll(int page, int size) {
        log.debug("Finding all products - page: {}, size: {}", page, size);
        return store.values().stream()
                .sorted((a, b) -> Long.compare(a.getId(), b.getId()))
                .skip((long) page * size)
                .limit(size)
                .toList();
    }

    /**
     * Retrieve a product by ID.
     *
     * @param id the product ID
     * @return the product
     * @throws ProductNotFoundException if not found
     */
    public Product findById(Long id) {
        log.debug("Finding product by id: {}", id);
        Product product = store.get(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    /**
     * Create a new product.
     *
     * @param request the creation request
     * @return the created product with generated ID and timestamps
     */
    public Product create(CreateProductRequest request) {
        log.debug("Creating product: {}", request.getName());
        Product product = productMapper.toModel(request);
        product.setId(idGenerator.getAndIncrement());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        store.put(product.getId(), product);
        log.info("Created product with id: {}", product.getId());
        return product;
    }

    /**
     * Update an existing product.
     *
     * @param id      the product ID
     * @param request the update request
     * @return the updated product
     * @throws ProductNotFoundException if not found
     */
    public Product update(Long id, CreateProductRequest request) {
        log.debug("Updating product id: {}", id);
        Product existing = findById(id);
        productMapper.updateModel(request, existing);
        existing.setUpdatedAt(LocalDateTime.now());
        store.put(id, existing);
        log.info("Updated product with id: {}", id);
        return existing;
    }

    /**
     * Delete a product by ID.
     *
     * @param id the product ID
     * @throws ProductNotFoundException if not found
     */
    public void delete(Long id) {
        log.debug("Deleting product id: {}", id);
        if (!store.containsKey(id)) {
            throw new ProductNotFoundException(id);
        }
        store.remove(id);
        log.info("Deleted product with id: {}", id);
    }
}
