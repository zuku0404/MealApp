package com.example.SpringSecondAppTest.cuisine;

import com.example.SpringSecondAppTest.meal.Meal;
import com.example.SpringSecondAppTest.preferences.FamilyPreference;
import com.example.SpringSecondAppTest.family_meal.FamilyMeal;
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
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private CuisineType cuisineType;

    @OneToMany(mappedBy = "cuisine")
    private Set<Meal> meals = new HashSet<>();

    @OneToMany(mappedBy = "cuisine")
    private Set<FamilyMeal> familyMeals = new HashSet<>();

    @ManyToMany(mappedBy = "cuisines")
    private Set<FamilyPreference> familyPreferences = new HashSet<>();

    public Cuisine(Long id, CuisineType cuisineType){
        this.id = id;
        this.cuisineType = cuisineType;
    }
}
