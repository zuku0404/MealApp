package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.preferences.FamilyPreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FamilyPreferenceSample {
    private FamilyPreferenceSample() {}

    public static final List<FamilyPreference> FAMILY_PREFERENCES;

    static {
        FAMILY_PREFERENCES = Collections.unmodifiableList(create());
    }

    public static List<FamilyPreference> create() {
        return new ArrayList<>(Arrays.asList(
                new FamilyPreference(1L),
                new FamilyPreference(2L)
        ));
    }
}
