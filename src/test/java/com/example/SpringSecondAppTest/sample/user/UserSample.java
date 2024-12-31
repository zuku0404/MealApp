package com.example.SpringSecondAppTest.sample.user;

import com.example.SpringSecondAppTest.account.Account;
import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.user.Role;
import com.example.SpringSecondAppTest.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserSample {
    private UserSample() {}

    public static List<User> create() {
        List<Account> accounts = AccountSample.create();
        List<Family> families = FamilySample.create();
        return new ArrayList<>(Arrays.asList(
                new User(1L, "admin", Role.ROLE_ADMIN, accounts.getFirst(), null),
                new User(2L, "user1", Role.ROLE_USER, accounts.get(1), families.get(1)),
                new User(3L, "user2", Role.ROLE_USER, accounts.get(2), families.get(2)),
                new User(4L, "user3", Role.ROLE_USER, accounts.get(3), families.get(3))
        ));
    }
}
