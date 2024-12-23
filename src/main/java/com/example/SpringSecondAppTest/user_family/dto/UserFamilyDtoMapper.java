package com.example.SpringSecondAppTest.user_family.dto;

import com.example.SpringSecondAppTest.user.dto.UserDtoMapper;
import com.example.SpringSecondAppTest.user_family.UserFamily;

import java.util.List;
import java.util.Set;

public class UserFamilyDtoMapper {
    private UserFamilyDtoMapper(){}

    public static List<UserFamilyWithoutFamilyDto> mapToUserFamilyWithoutFamilyDtos(Set<UserFamily> userFamilies){
        return userFamilies.stream()
                .map(UserFamilyDtoMapper::mapToUserFamilyWithoutFamilyDto)
                .toList();
    }

    public static UserFamilyWithoutFamilyDto mapToUserFamilyWithoutFamilyDto (UserFamily userFamily) {
        return new UserFamilyWithoutFamilyDto(
                userFamily.getId(),
                UserDtoMapper.mapToUserDto(userFamily.getFamilyMember()));
    }
}
