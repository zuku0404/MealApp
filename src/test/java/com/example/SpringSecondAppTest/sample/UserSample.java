package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.user.Role;
import com.example.SpringSecondAppTest.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserSample {
    public static final List<User> USERS = new ArrayList<>(Arrays.asList(
            new User(1L, "admin", Role.ROLE_ADMIN, AccountSample.ACCOUNTS.get(0), null),
            new User(2L, "user1", Role.ROLE_USER, AccountSample.ACCOUNTS.get(1), FamilySample.FAMILIES.get(1)),
            new User(3L, "user2", Role.ROLE_USER, AccountSample.ACCOUNTS.get(2), FamilySample.FAMILIES.get(2)),
            new User(4L, "user3", Role.ROLE_USER, AccountSample.ACCOUNTS.get(3), FamilySample.FAMILIES.get(3))
    ));
}
