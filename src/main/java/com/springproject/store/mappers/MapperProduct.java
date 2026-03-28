package com.springproject.store.mappers;

import com.springproject.store.dtos.ProductRequestDto;
import com.springproject.store.dtos.ProductResponseDto;
import com.springproject.store.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperProduct {
    ProductResponseDto toDto(Product product);

    Product toEntity(ProductRequestDto dto);
}
