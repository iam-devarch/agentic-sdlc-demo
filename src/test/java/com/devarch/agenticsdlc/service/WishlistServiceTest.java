package com.devarch.agenticsdlc.service;

import com.devarch.agenticsdlc.exception.ProductNotFoundException;
import com.devarch.agenticsdlc.exception.UserNotFoundException;
import com.devarch.agenticsdlc.model.Product;
import com.devarch.agenticsdlc.model.User;
import com.devarch.agenticsdlc.model.Wishlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private WishlistService wishlistService;

    private User sampleUser;
    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleUser = new User(1L, "Test User", "test@example.com");
        sampleProduct = new Product(10L, "Test Product", "Description", BigDecimal.TEN, null, null);
    }

    @Test
    void shouldGetOrCreateWishlist() {
        // Arrange
        when(userService.findById(1L)).thenReturn(sampleUser);

        // Act
        Wishlist wishlist = wishlistService.getOrCreateWishlist(1L);

        // Assert
        assertThat(wishlist).isNotNull();
        assertThat(wishlist.getUserId()).isEqualTo(1L);
        assertThat(wishlist.getProducts()).isEmpty();
        verify(userService).findById(1L);
    }

    @Test
    void shouldAddProductToWishlist() {
        // Arrange
        when(userService.findById(1L)).thenReturn(sampleUser);
        when(productService.findById(10L)).thenReturn(sampleProduct);

        // Act
        Wishlist wishlist = wishlistService.addProductToWishlist(1L, 10L);

        // Assert
        assertThat(wishlist.getProducts()).hasSize(1);
        assertThat(wishlist.getProducts()).contains(sampleProduct);
    }

    @Test
    void shouldNotAddDuplicateProductToWishlist() {
        // Arrange
        when(userService.findById(1L)).thenReturn(sampleUser);
        when(productService.findById(10L)).thenReturn(sampleProduct);

        // Act
        wishlistService.addProductToWishlist(1L, 10L);
        Wishlist wishlist = wishlistService.addProductToWishlist(1L, 10L);

        // Assert
        assertThat(wishlist.getProducts()).hasSize(1);
    }

    @Test
    void shouldRemoveProductFromWishlist() {
        // Arrange
        when(userService.findById(1L)).thenReturn(sampleUser);
        when(productService.findById(10L)).thenReturn(sampleProduct);
        wishlistService.addProductToWishlist(1L, 10L);

        // Act
        wishlistService.removeProductFromWishlist(1L, 10L);
        Wishlist wishlist = wishlistService.getOrCreateWishlist(1L);

        // Assert
        assertThat(wishlist.getProducts()).isEmpty();
    }

    @Test
    void shouldThrowUserNotFoundException() {
        // Arrange
        when(userService.findById(99L)).thenThrow(new UserNotFoundException(99L));

        // Act & Assert
        assertThatThrownBy(() -> wishlistService.getOrCreateWishlist(99L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void shouldThrowProductNotFoundException() {
        // Arrange
        when(userService.findById(1L)).thenReturn(sampleUser);
        when(productService.findById(999L)).thenThrow(new ProductNotFoundException(999L));

        // Act & Assert
        assertThatThrownBy(() -> wishlistService.addProductToWishlist(1L, 999L))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("999");
    }
}
