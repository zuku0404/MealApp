package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    @Query("select m from Meal m " +
            "left join fetch m.ingredientMeals im " +
            "left join fetch im.ingredient " +
            "where m.id = :id")
    Optional<Meal> findWithIngredientsById(@RequestParam("id") Long id);

    @Query("select m from Meal m " +
            "left join fetch m.cuisine " +
            "left join fetch m.ingredientMeals im " +
            "left join fetch im.ingredient")
    List<Meal> findAllWithDetails();

    List<Meal> findMealsByCuisineId(Long id, Pageable pageable);

    Optional<Meal> findMealByName(String name);

    @Query("select m from Meal m " +
            "left join m.cuisine c " +
            "where (:cuisine_types is null or c.cuisineType IN (:cuisine_types))")
    List<Meal> findByCuisineTypes(@Param("cuisine_types") List<CuisineType> cuisineTypes, Pageable pageable);
}
