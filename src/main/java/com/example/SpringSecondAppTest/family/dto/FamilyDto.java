package com.example.SpringSecondAppTest.family.dto;

import com.example.SpringSecondAppTest.user.dto.UserDto;
import lombok.Builder;

import java.util.Set;

@Builder
public record FamilyDto(
        Long id,
        String name,
        Set<UserDto> users
){
}
