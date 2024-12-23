package com.example.SpringSecondAppTest.sample.user;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.sample.FamilyPreferenceSample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FamilySample {
    private FamilySample(){}

    public static final List<Family> FAMILIES = new ArrayList<>(Arrays.asList(
            new Family(1L, "family_1", FamilyPreferenceSample.FAMILY_PREFERENCES.getFirst()),
            new Family(2L, "family_2", FamilyPreferenceSample.FAMILY_PREFERENCES.get(1))
    ));
}
