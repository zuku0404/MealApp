package com.example.SpringSecondAppTest.views.global_user_ingredients_view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalAndFamilyIngredientsViewRepository extends JpaRepository<GlobalAndFamilyIngredientsView, Long> {
    @Query("SELECT gafiv FROM GlobalAndFamilyIngredientsView gafiv " +
            "WHERE gafiv.familyId = :familyId")
    List<GlobalAndFamilyIngredientsView> findAllCreatedByFamily(@Param("familyId") Long familyId);

    @Query("select gafiv FROM GlobalAndFamilyIngredientsView gafiv " +
            "where gafiv.name in (:ingredientsNames)")
    List<GlobalAndFamilyIngredientsView> findAllByName(@Param("ingredientsNames")List<String> ingredientsNames);
}
