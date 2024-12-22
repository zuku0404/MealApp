package com.example.SpringSecondAppTest.family.dto;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.user.dto.UserMapper;

import java.util.List;
import java.util.Set;

public class FamilyMapper {
    private FamilyMapper() {
    }

    public static List<FamilyDto> mapToFamilyDtos(List<Family> family) {
        return family.stream()
                .map(FamilyMapper::mapToFamilyDto)
                .toList();
    }

    public static FamilyDto mapToFamilyDto(Family family) {
        return new FamilyDto.FamilyDtoBuilder()
                .id(family.getId())
                .name(family.getName())
                .users(UserMapper.mapToUserDtos(family.getFamilyMembers()))
                .build();
    }
}
