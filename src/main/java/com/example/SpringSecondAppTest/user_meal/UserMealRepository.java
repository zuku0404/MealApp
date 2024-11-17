package com.example.SpringSecondAppTest.user_meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMealRepository extends JpaRepository<UserMeal, Long> {
    Optional<UserMeal> findByNameAndUserId(String userMealName, Long userId);

    Optional<UserMeal> findByIdAndUserId(Long mealId, Long userId);

    List<UserMeal> findByName(String name);

    @Query("select distinct um from UserMeal um " +
            "left join fetch um.userIngredientMeals uim " +
            "left join fetch uim.userIngredient ui " +
            "left join fetch uim.ingredient i " +
            "left join fetch um.cuisine " +
            "left join fetch um.user u " +
            "where u.id = :user_id")
    List<UserMeal> findAllForUserByUserLogin(@Param("user_id") Long userId);

    @Query("select um from UserMeal um " +
            "left join fetch um.cuisine " +
            "left join um.user u " +
            "where u.id = :user_id")
    List<UserMeal> findAllWithBasic(@Param("user_id") Long userId);
}
