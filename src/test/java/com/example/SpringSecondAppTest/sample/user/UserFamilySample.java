package com.example.SpringSecondAppTest.sample.user;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_family.UserFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserFamilySample {
    private UserFamilySample() {
    }

    public static List<UserFamily> create() {
        List<Family> families = FamilySample.create();
        List<User> users = UserSample.create();
        return new ArrayList<>(Arrays.asList(
                new UserFamily(1L, users.get(1), families.getFirst()),
                new UserFamily(2L, users.get(1), families.get(1)),
                new UserFamily(3L, users.get(2), families.getFirst()),
                new UserFamily(4L, users.get(3), families.get(1))
        ));
    }
}
