package com.example.SpringSecondAppTest.views.global_user_ingredients_view;

import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalAndFamilyIngredientsViewRepository extends JpaRepository<GlobalAndFamilyIngredientsView,Long> {
    @Query ("SELECT gafiv FROM GlobalAndFamilyIngredientView gafiv " +
            "WHERE gafiv.familyId = :familyId")
    List<GlobalAndFamilyIngredientsView> findAllCreatedByFamily(Long familyId);

    @Query("select gafiv FROM GlobalAndFamilyIngredientsView gafiv " +
            "where gafiv.name in (:ingredientsNames)")
    List<GlobalAndFamilyIngredientsView> findAllByName(List<String> ingredientsNames);
}
