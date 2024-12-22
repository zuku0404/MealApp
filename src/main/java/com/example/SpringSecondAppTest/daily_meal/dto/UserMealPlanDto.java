package com.example.SpringSecondAppTest.daily_meal.dto;

import java.time.LocalDate;

public record UserMealPlanDto(
        Long id,
        LocalDate localDate,
        String name,
        String imageUrl) {
}
