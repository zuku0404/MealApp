package com.example.SpringSecondAppTest.family_composition;

import com.example.SpringSecondAppTest.family.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyMealCompositionRepository extends JpaRepository<FamilyMealComposition, Long> {
    @Query("select distinct fm.family from FamilyMealComposition fmc " +
            "left join fmc.familyMeal fm " +
            "where fmc.ingredient.id = :ingredientId ")
    List<Family> findFamilyIdsForIngredientId(Long ingredientId);

    @Query("select fmc from FamilyMealComposition fmc " +
            "where fmc.ingredient.id = :ingredientId and " +
            "fmc.familyMeal.family.id = :familyId ")
    List<FamilyMealComposition> findByIngredientIdAndUserId(Long ingredientId,Long familyId);
}
