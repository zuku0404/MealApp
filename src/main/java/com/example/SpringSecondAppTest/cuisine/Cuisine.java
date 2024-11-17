package com.example.SpringSecondAppTest.cuisine;

import com.example.SpringSecondAppTest.meal.Meal;
import com.example.SpringSecondAppTest.preferences.UserPreference;
import com.example.SpringSecondAppTest.user_meal.UserMeal;
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
    private Set<UserMeal> userMeals = new HashSet<>();

    @ManyToMany(mappedBy = "cuisines")
    private Set<UserPreference> userPreferences = new HashSet<>();
}
