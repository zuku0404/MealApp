package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.CuisineRepository;
import com.example.SpringSecondAppTest.family_meal.FamilyMealRepository;
import com.example.SpringSecondAppTest.ingerdient.IngredientRepository;
import com.example.SpringSecondAppTest.meal_composition.MealCompositionRepository;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsViewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MealServiceTest {
    @Mock
    MealRepository mealRepository;
    @Mock
    CuisineRepository cuisineRepository;
    @Mock
    IngredientRepository ingredientRepository;
    @Mock
    FamilyMealRepository familyMealRepository;
    @Mock
    MealCompositionRepository mealCompositionRepository;
    @Mock
    GlobalAndFamilyMealsViewRepository globalAndFamilyMealsViewRepository;

    @InjectMocks
    MealService mealService;

    @Nested
    @DisplayName("Tests for getAllWithBasicData")
    class GetAllWithBasicData {
        @Test
        void shouldReturnAllTasksWhenNoFiltersAndSortingDisabled() {

        }
    }
}
