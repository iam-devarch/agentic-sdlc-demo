# EPIC-WISHLIST: User Wishlist Management

## Business Context
The Wishlist feature allows users to save products they are interested in for future purchase. This enhances user engagement and provides valuable data on product interest.

## Goals
- Allow users to persist a list of desired products.
- Ensure a seamless integration with the existing product catalog.
- Provide a robust API for managing wishlist items.

## Scope
### In-Scope
- Add/Remove products to/from a user's wishlist.
- View all products in a user's wishlist.
- Automatic creation of a wishlist for a user if it doesn't exist.
- Validation of user and product existence.
- Prevention of duplicate entries in the wishlist.

### Out-of-Scope
- Multiple wishlists per user.
- Sharing wishlists with others.
- Moving items from wishlist to cart (future feature).

## Success Criteria
- Users can successfully manage their wishlists via REST API.
- Proper error messages are returned for non-existent users/products.
- Duplicate products are not added to the wishlist.
- All tests (unit, contract) pass successfully.

## User Stories

### US-1: Add Product to Wishlist
**As a** registered user  
**I want to** add a product to my wishlist  
**So that** I can save it for later consideration  

**Acceptance Criteria:**
- [ ] User can add a valid product to their wishlist using `POST /api/v1/wishlist/{userId}/add/{productId}`.
- [ ] If the wishlist doesn't exist for the user, it is created automatically.
- [ ] If the product is already in the wishlist, it should not be added again (idempotent).
- [ ] Return 404 Not Found if the user does not exist.
- [ ] Return 404 Not Found if the product does not exist.
- [ ] Return 201 Created on success with the updated wishlist content.

**Priority**: High  
**Size**: M

### US-2: Remove Product from Wishlist
**As a** registered user  
**I want to** remove a product from my wishlist  
**So that** I can clean up my list  

**Acceptance Criteria:**
- [ ] User can remove a product using `DELETE /api/v1/wishlist/{userId}/remove/{productId}`.
- [ ] Return 204 No Content on successful removal.
- [ ] Return 404 Not Found if the user does not exist.
- [ ] Return 404 Not Found if the product is not in the wishlist (optional, or just 204 for idempotency - requirement says "meaningful response").
- [ ] Return 200 OK or 204 No Content even if the product wasn't in the list (following idempotency best practices).

**Priority**: High  
**Size**: S

### US-3: View Wishlist
**As a** registered user  
**I want to** view all products in my wishlist  
**So that** I can see what I have saved  

**Acceptance Criteria:**
- [ ] User can view their wishlist using `GET /api/v1/wishlist/{userId}`.
- [ ] Return 200 OK with a list of products (DTOs).
- [ ] Return 404 Not Found if the user does not exist.
- [ ] Return empty list if the wishlist is empty or doesn't exist yet (or create empty on first access).

**Priority**: High  
**Size**: S
