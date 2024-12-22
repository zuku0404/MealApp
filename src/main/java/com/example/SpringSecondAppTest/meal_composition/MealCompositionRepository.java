package com.example.SpringSecondAppTest.meal_composition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealCompositionRepository extends JpaRepository<MealComposition,Long> {
}
