package com.example.SpringSecondAppTest.user_ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserIngredientRepository extends JpaRepository<UserIngredient,Long> {

    @Query("select ui from UserIngredient ui " +
            "where ui.name = :name")
    List<UserIngredient> findByName(@Param("name") String name);

    @Query("select ui from UserIngredient ui " +
            "left join ui.user u " +
            "left join u.account a " +
            "where a.login = :login")
    List<UserIngredient> findAllForUserByUserLogin(@Param("login") String login);

    Optional<UserIngredient> findByUserIdAndName(Long id1, String updatedUserName);
}
