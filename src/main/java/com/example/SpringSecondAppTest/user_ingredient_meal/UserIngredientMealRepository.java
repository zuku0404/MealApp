package com.example.SpringSecondAppTest.user_ingredient_meal;

import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserIngredientMealRepository extends JpaRepository<UserIngredientMeal, Long> {
    @Query("select distinct um.user from UserIngredientMeal umi " +
            "left join umi.userMeal um " +
            "where umi.ingredient.id = :id ")
    List<User> findUserIdsForIngredientId(@Param("id") Long ingredientId);

    @Query("select uim from UserIngredientMeal uim " +
            "where uim.ingredient.id = :ingredient_id and uim.userMeal.user.id = :user_id ")
    List<UserIngredientMeal> findByIngredientIdAndUserId(@Param("ingredient_id") Long ingredientId, @Param("user_id") Long userId);
}
