package com.example.SpringSecondAppTest.test.entity.dto;

import com.example.SpringSecondAppTest.test.entity.Product;

import java.util.List;

public class ProductToDtoMapper {

    private ProductToDtoMapper(){}

    public static List<ProductDto> mapToDtos(List<Product> products){
        return products.stream()
                .map(ProductToDtoMapper::mapProductToDto)
                .toList();
    }

    public static ProductDto mapProductToDto(Product product) {
        return new ProductDto.ProductDtoBuilder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }
}
