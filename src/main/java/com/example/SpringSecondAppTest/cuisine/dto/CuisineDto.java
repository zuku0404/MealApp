package com.example.SpringSecondAppTest.cuisine.dto;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import lombok.Builder;

@Builder
public record CuisineDto(Long id, CuisineType cuisineType) { }
