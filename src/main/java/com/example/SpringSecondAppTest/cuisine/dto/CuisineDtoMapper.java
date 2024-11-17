package com.example.SpringSecondAppTest.cuisine.dto;

import com.example.SpringSecondAppTest.cuisine.Cuisine;

import java.util.List;

public class CuisineDtoMapper {
    private CuisineDtoMapper(){}

    public static List<CuisineDto> mapToCuisineDtos(List<Cuisine> cuisines) {
        return cuisines.stream()
                .map(CuisineDtoMapper::mapToCuisineDto)
                .toList();
    }

    private static CuisineDto mapToCuisineDto(Cuisine cuisine) {
        return new CuisineDto.CuisineDtoBuilder()
                .id(cuisine.getId())
                .cuisineType(cuisine.getCuisineType())
                .build();
    }
}
