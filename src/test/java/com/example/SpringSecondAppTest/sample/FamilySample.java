package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.family.Family;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FamilySample {
    public static final List<Family> FAMILIES = new ArrayList<>(Arrays.asList(
            new Family(1L, "family_1", FamilyPreferenceSample.FAMILY_PREFERENCES.get(0)),
            new Family(2L, "family_2", FamilyPreferenceSample.FAMILY_PREFERENCES.get(1))
    ));
}
