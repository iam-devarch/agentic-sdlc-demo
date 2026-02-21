package com.devarch.agenticsdlc.mapper;

import com.devarch.agenticsdlc.dto.CreateProductRequest;
import com.devarch.agenticsdlc.dto.ProductDto;
import com.devarch.agenticsdlc.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * MapStruct mapper for converting between Product domain model and DTOs.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    /**
     * Convert domain model to response DTO.
     */
    ProductDto toDto(Product product);

    /**
     * Convert creation request to domain model.
     * ID, createdAt, and updatedAt are set by the service layer.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toModel(CreateProductRequest request);

    /**
     * Update existing domain model from request DTO.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateModel(CreateProductRequest request, @MappingTarget Product product);
}
