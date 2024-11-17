package com.example.SpringSecondAppTest.ingerdient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);

    @Query("SELECT i FROM Ingredient i " +
            "WHERE (:ingredient_category IS NULL OR i.ingredientCategory IN (:ingredient_category))")
    List<Ingredient> findForCategories(@Param("ingredient_category") List<IngredientCategory> ingredientCategory);

    @Query("select i from Ingredient i " +
            "where i.name in (:name_list)")
    List<Ingredient> findAllByName(@Param("name_list") List<String> ingredientsNames);
}
