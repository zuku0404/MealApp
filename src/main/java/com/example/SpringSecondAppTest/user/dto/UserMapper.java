package com.example.SpringSecondAppTest.user.dto;

import com.example.SpringSecondAppTest.user.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {
    }

    public static Set<UserDto> mapToUserDtos(Set<User> users) {
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toSet());
    }


    public static UserDto mapToUserDto(User user) {
        return new UserDto.UserDtoBuilder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
