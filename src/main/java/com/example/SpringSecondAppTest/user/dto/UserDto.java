package com.example.SpringSecondAppTest.user.dto;

import lombok.Builder;

@Builder
public record UserDto(
        Long id,
        String name) {
}
