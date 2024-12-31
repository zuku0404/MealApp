package com.example.SpringSecondAppTest.sample.user;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.preferences.FamilyPreference;
import com.example.SpringSecondAppTest.sample.FamilyPreferenceSample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FamilySample {
    private FamilySample(){}

    public static final List<Family> FAMILIES;

    static {
        FAMILIES = Collections.unmodifiableList(create());
    }

    public static List<Family> create() {
        List<FamilyPreference> familyPreferences = FamilyPreferenceSample.create();
        return new ArrayList<>(Arrays.asList(
                new Family(1L, "family_1", familyPreferences.getFirst()),
                new Family(2L, "family_2", familyPreferences.get(1))
        ));
    }
}
