package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.preferences.FamilyPreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FamilyPreferenceSample {
    public static final List<FamilyPreference> FAMILY_PREFERENCES = new ArrayList<>(Arrays.asList(
            new FamilyPreference(1L),
            new FamilyPreference(2L)
    ));
}
