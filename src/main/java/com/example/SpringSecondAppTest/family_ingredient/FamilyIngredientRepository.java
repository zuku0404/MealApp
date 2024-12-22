package com.example.SpringSecondAppTest.family_ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyIngredientRepository extends JpaRepository<FamilyIngredient,Long> {
    @Query("select fi from FamilyIngredient fi " +
            "where fi.name = :ingredientName")
    List<FamilyIngredient> findByName(String ingredientName);

    @Query("select fi from FamilyIngredient fi " +
            "where fi.family.id = :familyId")
    List<FamilyIngredient> findAllForCurrentFamily(Long familyId);

    @Query("select fi from FamilyIngredient fi " +
            "where fi.family.id = :familyId and fi.name = :updatedUserName")
    Optional<FamilyIngredient> findByFamilyIdAndName(Long familyId, String updatedUserName);
}
