package com.example.SpringSecondAppTest.family.dto;

import jakarta.validation.constraints.NotBlank;

public record FamilyDtoWithoutId(
        @NotBlank
        String name
) {
}
