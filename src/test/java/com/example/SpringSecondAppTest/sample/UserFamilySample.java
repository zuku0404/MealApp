package com.example.SpringSecondAppTest.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserFamilySample {
    public static final List<UserFamily> USER_FAMILIES = new ArrayList<>(Arrays.asList(
            new UserFamily(FamilySample.FAMILIES.get(0), UserSample.USERS.get(1)),
            new UserFamily(FamilySample.FAMILIES.get(1), UserSample.USERS.get(1)),
            new UserFamily(FamilySample.FAMILIES.get(0), UserSample.USERS.get(2)),
            new UserFamily(FamilySample.FAMILIES.get(1), UserSample.USERS.get(3))
    ));
}
