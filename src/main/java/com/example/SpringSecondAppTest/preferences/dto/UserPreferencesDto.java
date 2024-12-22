package com.example.SpringSecondAppTest.preferences.dto;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.cuisine.dto.CuisineDto;

import java.util.Set;

public record UserPreferencesDto(
        Long id,
        Set<CuisineDto> cuisines
) {
}
