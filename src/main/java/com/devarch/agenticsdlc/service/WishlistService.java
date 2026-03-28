package com.devarch.agenticsdlc.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.devarch.agenticsdlc.model.Product;
import com.devarch.agenticsdlc.model.Wishlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for managing User Wishlists.
 * Uses an in-memory store for demo purposes.
 */
@Service
public class WishlistService {

    private static final Logger log = LoggerFactory.getLogger(WishlistService.class);
    private final UserService userService;
    private final ProductService productService;
    private final Map<Long, Wishlist> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Constructor injection.
     */
    public WishlistService(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    /**
     * Retrieves the wishlist for a user.
     * Creates it if it doesn't exist.
     *
     * @param userId the user ID
     * @return the wishlist
     * @throws com.devarch.agenticsdlc.exception.UserNotFoundException if user doesn't exist
     */
    public Wishlist getOrCreateWishlist(Long userId) {
        log.debug("Getting or creating wishlist for user: {}", userId);
        userService.findById(userId); // Validate user exists

        return store.values().stream()
                .filter(w -> w.getUserId().equals(userId))
                .findFirst()
                .orElseGet(() -> {
                    Wishlist wishlist = new Wishlist(idGenerator.getAndIncrement(), userId);
                    store.put(wishlist.getId(), wishlist);
                    log.info("Created new wishlist for user: {}", userId);
                    return wishlist;
                });
    }

    /**
     * Adds a product to the user's wishlist.
     *
     * @param userId    the user ID
     * @param productId the product ID
     * @return the updated wishlist
     */
    public Wishlist addProductToWishlist(Long userId, Long productId) {
        log.debug("Adding product {} to wishlist of user {}", productId, userId);
        Wishlist wishlist = getOrCreateWishlist(userId);
        Product product = productService.findById(productId);

        boolean added = wishlist.addProduct(product);
        if (added) {
            log.info("Added product {} to wishlist {}", productId, wishlist.getId());
        } else {
            log.debug("Product {} already in wishlist {}", productId, wishlist.getId());
        }
        return wishlist;
    }

    /**
     * Removes a product from the user's wishlist.
     *
     * @param userId    the user ID
     * @param productId the product ID
     */
    public void removeProductFromWishlist(Long userId, Long productId) {
        log.debug("Removing product {} from wishlist of user {}", productId, userId);
        Wishlist wishlist = getOrCreateWishlist(userId);
        boolean removed = wishlist.removeProduct(productId);
        if (removed) {
            log.info("Removed product {} from wishlist {}", productId, wishlist.getId());
        } else {
            log.warn("Product {} not found in wishlist {}", productId, wishlist.getId());
        }
    }
}
