package com.example.SpringSecondAppTest.meal.dto;

import com.example.SpringSecondAppTest.views.Source;

public record BasicMealDto(
        Long id,
        Source source,
        String name,
        String imageUrl) {
}
