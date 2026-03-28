package com.devarch.agenticsdlc.controller;

import com.devarch.agenticsdlc.api.model.ProductDto;
import com.devarch.agenticsdlc.api.model.WishlistDto;
import com.devarch.agenticsdlc.exception.UserNotFoundException;
import com.devarch.agenticsdlc.mapper.WishlistMapper;
import com.devarch.agenticsdlc.model.Wishlist;
import com.devarch.agenticsdlc.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishlistController.class)
class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishlistService wishlistService;

    @MockitoBean
    private WishlistMapper wishlistMapper;

    private WishlistDto wishlistDto;

    @BeforeEach
    void setUp() {
        wishlistDto = new WishlistDto(1L, 1L);
        wishlistDto.setProducts(List.of(new ProductDto(10L, "Product", 10.0)));
    }

    @Test
    void shouldGetWishlist() throws Exception {
        // Arrange
        when(wishlistService.getOrCreateWishlist(1L)).thenReturn(new Wishlist(1L, 1L));
        when(wishlistMapper.toDto(any(Wishlist.class))).thenReturn(wishlistDto);

        // Act & Assert
        mockMvc.perform(get("/api/v1/wishlist/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.products[0].name").value("Product"));
    }

    @Test
    void shouldAddProductToWishlist() throws Exception {
        // Arrange
        when(wishlistService.addProductToWishlist(1L, 10L)).thenReturn(new Wishlist(1L, 1L));
        when(wishlistMapper.toDto(any(Wishlist.class))).thenReturn(wishlistDto);

        // Act & Assert
        mockMvc.perform(post("/api/v1/wishlist/1/add/10"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(1L));
    }

    @Test
    void shouldRemoveProductFromWishlist() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/v1/wishlist/1/remove/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404WhenUserNotFound() throws Exception {
        // Arrange
        when(wishlistService.getOrCreateWishlist(99L)).thenThrow(new UserNotFoundException(99L));

        // Act & Assert
        mockMvc.perform(get("/api/v1/wishlist/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found with id: 99"));
    }
}
