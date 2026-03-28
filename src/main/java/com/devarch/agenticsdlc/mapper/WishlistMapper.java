package com.devarch.agenticsdlc.mapper;

import com.devarch.agenticsdlc.api.model.WishlistDto;
import com.devarch.agenticsdlc.model.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for converting between Wishlist domain model and generated API DTOs.
 */
@Mapper(componentModel = "spring", uses = {ProductApiMapper.class})
public interface WishlistMapper {

    /**
     * Map domain model to generated DTO.
     */
    @Mapping(target = "userId", source = "userId")
    WishlistDto toDto(Wishlist wishlist);
}
