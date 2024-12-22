package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    @Query("select m from Meal m " +
            "left join fetch m.mealCompositions im " +
            "left join fetch im.ingredient " +
            "where m.id = :id")
    Optional<Meal> findWithIngredientsById(Long id);

    List<Meal> findMealsByCuisineId(Long id, Pageable pageable);

    Optional<Meal> findMealByName(String name);

    @Query("select m from Meal m " +
            "left join m.cuisine c " +
            "where (:cuisineTypes is null or c.cuisineType IN (:cuisineTypes))")
    List<Meal> findByCuisineTypes(List<CuisineType> cuisineTypes);
}
