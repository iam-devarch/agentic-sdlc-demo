# DESIGN-WISHLIST-API: Wishlist Management API

## Overview
The Wishlist API allows users to manage a personal list of products they are interested in. It supports adding products, removing products, and viewing the current wishlist.

## Base Path
`/api/v1/wishlist`

## Endpoints

### 1. Add Product to Wishlist
- **Method**: `POST`
- **Path**: `/{userId}/add/{productId}`
- **Description**: Adds a product to the user's wishlist. Creates the wishlist if it doesn't exist.
- **Path Variables**:
    - `userId` (Long): The ID of the user.
    - `productId` (Long): The ID of the product to add.
- **Responses**:
    - `201 Created`: Product added successfully. Returns `WishlistDto`.
    - `404 Not Found`: User or Product not found.
    - `400 Bad Request`: Invalid request.

### 2. Remove Product from Wishlist
- **Method**: `DELETE`
- **Path**: `/{userId}/remove/{productId}`
- **Description**: Removes a product from the user's wishlist.
- **Path Variables**:
    - `userId` (Long): The ID of the user.
    - `productId` (Long): The ID of the product to remove.
- **Responses**:
    - `204 No Content`: Product removed successfully (even if it wasn't there).
    - `404 Not Found`: User not found.

### 3. Get Wishlist
- **Method**: `GET`
- **Path**: `/{userId}`
- **Description**: Retrieves the wishlist for a specific user.
- **Path Variables**:
    - `userId` (Long): The ID of the user.
- **Responses**:
    - `200 OK`: Returns `WishlistDto`.
    - `404 Not Found`: User not found.

## Data Models

### UserDto
- `id` (Long): Unique identifier.
- `name` (String): User's name.
- `email` (String): User's email.

### WishlistDto
- `id` (Long): Unique identifier.
- `userId` (Long): ID of the owner.
- `products` (List<ProductDto>): List of products in the wishlist.

### ProductDto (Existing)
- `id` (Long)
- `name` (String)
- `description` (String)
- `price` (BigDecimal)

## Error Handling
Standard error response format:
```json
{
  "timestamp": "2026-03-28T10:30:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with id: 123",
  "path": "/api/v1/wishlist/123"
}
```
