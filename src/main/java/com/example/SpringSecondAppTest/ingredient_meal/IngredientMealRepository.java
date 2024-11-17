package com.example.SpringSecondAppTest.ingredient_meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientMealRepository extends JpaRepository<IngredientMeal,Long> {
    List<IngredientMeal> findByMealId(Long id);
}
