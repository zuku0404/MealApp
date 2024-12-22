package com.example.SpringSecondAppTest.ingerdient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);

    @Query("SELECT i FROM Ingredient i " +
            "WHERE (:ingredientCategory IS NULL OR i.ingredientCategory IN (:ingredientCategory))")
    List<Ingredient> findForCategories(List<IngredientCategory> ingredientCategory);

    @Query("select i from Ingredient i " +
            "where i.name in (:ingredientsNames)")
    List<Ingredient> findAllByName(List<String> ingredientsNames);
}
