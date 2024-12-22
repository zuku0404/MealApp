package com.example.SpringSecondAppTest.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        String firstName,
        String lastName,
        @NotBlank(message = "Login cannot be empty")
        String login,
        @Size(min = 4, message = "Password must be at least 4 characters")
        String password,
        FamilyCreationType familyCreationType,
        String familyName,
        Long id) {
}
