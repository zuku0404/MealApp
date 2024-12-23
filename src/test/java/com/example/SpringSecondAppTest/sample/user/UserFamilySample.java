package com.example.SpringSecondAppTest.sample.user;

import com.example.SpringSecondAppTest.user_family.UserFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserFamilySample {
    private UserFamilySample(){}

    public static final List<UserFamily> USER_FAMILIES = new ArrayList<>(Arrays.asList(
            new UserFamily(1L, UserSample.USERS.get(1), FamilySample.FAMILIES.getFirst()),
            new UserFamily(2L, UserSample.USERS.get(1), FamilySample.FAMILIES.get(1)),
            new UserFamily(3L, UserSample.USERS.get(2), FamilySample.FAMILIES.getFirst()),
            new UserFamily(4L, UserSample.USERS.get(3), FamilySample.FAMILIES.get(1))
    ));
}
