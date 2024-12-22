package com.example.SpringSecondAppTest.views.global_user_meals_view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GlobalAndFamilyMealsViewRepository extends JpaRepository<GlobalAndFamilyMealsView,Long> {
    @Query ("SELECT gafmv FROM GlobalAndFamilyMealsView gafmv " +
            "WHERE gafmv.familyId is NULL OR gafmv.familyId = :familyId")
    List<GlobalAndFamilyMealsView> findAllForFamily(Long familyId);

    @Query ("SELECT gafmv FROM GlobalAndFamilyMealsView gafmv " +
            "WHERE gafmv.familyId = :familyId")
    List<GlobalAndFamilyMealsView> findAllCreatedByFamily(Long familyId);

    @Query ("SELECT gafmv FROM GlobalAndFamilyMealsView gafmv " +
            "WHERE gafmv.familyId is NULL")
    List<GlobalAndFamilyMealsView> findForUnloggedUser();

    @Query("SELECT gafmv FROM GlobalAndFamilyMealsView gafmv " +
            "WHERE gafmv.name = :meal_name AND (gafmv.familyId is NULL OR gafmv.familyId = :family_id)")
    Optional<GlobalAndFamilyMealsView> findByNameAndAvailableForFamilyId(@Param("meal_name") String mealName, @Param("family_id") Long familyId);

    List<GlobalAndFamilyMealsView> findByName(String name);
}
