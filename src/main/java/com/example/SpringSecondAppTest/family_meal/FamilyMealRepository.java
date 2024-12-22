package com.example.SpringSecondAppTest.family_meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyMealRepository extends JpaRepository<FamilyMeal, Long> {
    Optional<FamilyMeal> findByNameAndFamilyId(String mealName, Long familyId);

    Optional<FamilyMeal> findByIdAndFamilyId(Long mealId, Long familyId);

    @Query("select fm from FamilyMeal fm " +
            "left join fetch fm.cuisine " +
            "left join fm.family f " +
            "where f.id = :familyId")
    List<FamilyMeal> findAllByFamily(Long familyId);
}
