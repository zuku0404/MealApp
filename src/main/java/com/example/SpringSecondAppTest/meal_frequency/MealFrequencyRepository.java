package com.example.SpringSecondAppTest.meal_frequency;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealFrequencyRepository extends JpaRepository<MealFrequency,Long> {
    Optional<MealFrequency> findByMealAndFamily(GlobalAndFamilyMealsView meal, Family userCurrentFamily);
}
