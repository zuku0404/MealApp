package com.example.SpringSecondAppTest.user.dto;

import com.example.SpringSecondAppTest.family.dto.FamilyDto;
import com.example.SpringSecondAppTest.user.Role;

public record UserDto(
        Long id,
        String name,
        Role role,
        FamilyDto currentFamily) {
}
