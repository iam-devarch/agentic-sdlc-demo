package com.devarch.agenticsdlc.controller;

import com.devarch.agenticsdlc.api.WishlistApi;
import com.devarch.agenticsdlc.api.model.WishlistDto;
import com.devarch.agenticsdlc.mapper.WishlistMapper;
import com.devarch.agenticsdlc.model.Wishlist;
import com.devarch.agenticsdlc.service.WishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Wishlist Management.
 * Implements the generated WishlistApi interface.
 */
@RestController
@RequestMapping("/api/v1")
public class WishlistController implements WishlistApi {

    private static final Logger log = LoggerFactory.getLogger(WishlistController.class);
    private final WishlistService wishlistService;
    private final WishlistMapper wishlistMapper;

    /**
     * Constructor injection as per SDLC guidelines.
     */
    public WishlistController(WishlistService wishlistService, WishlistMapper wishlistMapper) {
        this.wishlistService = wishlistService;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    public ResponseEntity<WishlistDto> getWishlist(Long userId) {
        log.debug("GET /api/v1/wishlist/{}", userId);
        Wishlist wishlist = wishlistService.getOrCreateWishlist(userId);
        return ResponseEntity.ok(wishlistMapper.toDto(wishlist));
    }

    @Override
    public ResponseEntity<WishlistDto> addProductToWishlist(Long userId, Long productId) {
        log.debug("POST /api/v1/wishlist/{}/add/{}", userId, productId);
        Wishlist wishlist = wishlistService.addProductToWishlist(userId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistMapper.toDto(wishlist));
    }

    @Override
    public ResponseEntity<Void> removeProductFromWishlist(Long userId, Long productId) {
        log.debug("DELETE /api/v1/wishlist/{}/remove/{}", userId, productId);
        wishlistService.removeProductFromWishlist(userId, productId);
        return ResponseEntity.noContent().build();
    }
}
