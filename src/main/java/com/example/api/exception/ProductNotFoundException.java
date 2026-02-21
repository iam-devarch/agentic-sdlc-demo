package com.example.api.exception;

/**
 * Exception thrown when a requested product is not found.
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id);
    }
}
