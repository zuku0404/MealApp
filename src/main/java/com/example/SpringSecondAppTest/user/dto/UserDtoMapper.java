package com.example.SpringSecondAppTest.user.dto;

import com.example.SpringSecondAppTest.family.dto.FamilyMapper;
import com.example.SpringSecondAppTest.user.User;

import java.util.List;
import java.util.Set;

public class UserDtoMapper {
    private UserDtoMapper() {
    }

    public static List<UserDto> mapToUserDtos(Set<User> users) {
        return users.stream()
                .map(UserDtoMapper::mapToUserDto)
                .toList();
    }


    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getRole(),
                FamilyMapper.mapToFamilyDto(user.getCurrentFamily()));
    }
}