package com.example.SpringSecondAppTest.cuisine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine,Long> {
    Optional<Cuisine> findByCuisineType(CuisineType cuisineType);
}
