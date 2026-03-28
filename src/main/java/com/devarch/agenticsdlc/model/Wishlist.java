package com.devarch.agenticsdlc.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Domain model representing a User's Wishlist.
 */
public class Wishlist {

    private Long id;
    private Long userId;
    private Set<Product> products = new HashSet<>();
    private LocalDateTime updatedAt;

    public Wishlist() {
    }

    public Wishlist(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Adds a product to the wishlist if not already present.
     * @param product the product to add
     * @return true if added, false if already present
     */
    public boolean addProduct(Product product) {
        this.updatedAt = LocalDateTime.now();
        return this.products.add(product);
    }

    /**
     * Removes a product from the wishlist.
     * @param productId the ID of the product to remove
     * @return true if removed, false if not found
     */
    public boolean removeProduct(Long productId) {
        this.updatedAt = LocalDateTime.now();
        return this.products.removeIf(p -> p.getId().equals(productId));
    }
}
