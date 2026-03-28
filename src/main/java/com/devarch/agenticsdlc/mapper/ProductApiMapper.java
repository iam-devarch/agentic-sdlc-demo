package com.devarch.agenticsdlc.mapper;

import com.devarch.agenticsdlc.api.model.ProductDto;
import com.devarch.agenticsdlc.model.Product;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between Product domain model and generated API DTOs.
 */
@Mapper(componentModel = "spring")
public interface ProductApiMapper {

    /**
     * Map domain model to generated DTO.
     */
    ProductDto toDto(Product product);
}
