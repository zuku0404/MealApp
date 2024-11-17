package com.example.SpringSecondAppTest.global_user_ingredients_view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GlobalAndUserIngredientsViewRepository extends JpaRepository<GlobalAndUserIngredientsView,Long> {
    @Query ("SELECT gauiv FROM GlobalAndUserIngredientsView gauiv " +
            "WHERE gauiv.source = 'GLOBAL' " +
            "OR (gauiv.source = 'USER' AND gauiv.id = :user_id)")
    List<GlobalAndUserIngredientsView> findIngredientsForUserName(@Param("user_id") Long userId);

    @Query("select gauiv FROM GlobalAndUserIngredientsView gauiv " +
            "where gauiv.name in (:ingredientsNames)")
    List<GlobalAndUserIngredientsView> findAllByName(@Param("ingredientsNames") List<String> ingredientsNames);
}
