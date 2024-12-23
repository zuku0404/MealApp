package com.example.SpringSecondAppTest.user_family.dto;

import com.example.SpringSecondAppTest.user.dto.UserDto;

public record UserFamilyWithoutFamilyDto(
        Long id,
        UserDto user) {
}
