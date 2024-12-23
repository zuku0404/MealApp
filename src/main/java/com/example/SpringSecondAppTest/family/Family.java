package com.example.SpringSecondAppTest.family;

import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import com.example.SpringSecondAppTest.family_meal.FamilyMeal;
import com.example.SpringSecondAppTest.preferences.FamilyPreference;
import com.example.SpringSecondAppTest.user_family.UserFamily;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "family")
    private Set<FamilyIngredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "family")
    private Set<FamilyMeal> meals = new HashSet<>();

    @OneToMany(mappedBy = "family")
    private Set<UserFamily> families = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_id")
    private FamilyPreference preference;


    public Family(Long id, String name, FamilyPreference preference){
        this.id = id;
        this.name = name;
        this.preference = preference;
    }

    public Family(String name) {
        this.name = name;
    }
}
