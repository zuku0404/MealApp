package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.CuisineRepository;
import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.family_meal.FamilyMealRepository;
import com.example.SpringSecondAppTest.ingerdient.IngredientRepository;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.MealDtoMapper;
import com.example.SpringSecondAppTest.meal_composition.MealCompositionRepository;
import com.example.SpringSecondAppTest.sample.meal.MealSample;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsViewRepository;
import liquibase.exception.DatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MealServiceTest {

    @Autowired
    private MockMvc mockMvc;
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

    private List<Meal> meals;

    @BeforeEach
    public void setup() {
        this.meals = MealSample.create();
    }


    @Nested
    @DisplayName("Tests for getAllWithBasicData method")
    class GetAllWithBasicData {
        @Test
        void shouldReturnBasicMealDtosWhenCuisineTypesExist() {
            List<CuisineType> cuisineTypes = new ArrayList<>(List.of(CuisineType.POLISH));
            List<BasicMealDto> expectedResult = mockMealsByCuisineTypes(cuisineTypes);
            List<BasicMealDto> result = mealService.getAllWithBasicData(cuisineTypes);
            assertThat(result)
                    .isNotEmpty()
                    .hasSize(2)
                    .containsExactlyInAnyOrderElementsOf(expectedResult);
            verify(mealRepository, times(1)).findByCuisineTypes(cuisineTypes);
        }

        @Test
        void shouldReturnAllMealsForAllCusinesType(){
            List<CuisineType> cuisineTypes = Arrays.stream(CuisineType.values()).toList();
            List<BasicMealDto> expectedResult = mockMealsByCuisineTypes(cuisineTypes);
            List<BasicMealDto> result = mealService.getAllWithBasicData(cuisineTypes);
            assertThat(result)
                    .isNotEmpty()
                    .containsExactlyInAnyOrderElementsOf(expectedResult)
                    .hasSize(meals.size());
        }


        @Test
        void shouldReturnEmptyListWhenNoMealsMatchCuisineTypes() {
            List<CuisineType> cuisineTypes = new ArrayList<>(List.of(CuisineType.JAPANESE));
            List<BasicMealDto> expectedResult = mockMealsByCuisineTypes(cuisineTypes);
            List<BasicMealDto> result = mealService.getAllWithBasicData(cuisineTypes);
            assertThat(result)
                    .containsExactlyInAnyOrderElementsOf(expectedResult)
                    .isEmpty();
        }

        @Test
        void shouldReturnEmptyListWhenCuisineTypesListIsEmpty() {
            List<CuisineType> cuisineTypes = new ArrayList<>();
            List<BasicMealDto> expectedResult = mockMealsByCuisineTypes(cuisineTypes);
            List<BasicMealDto> result = mealService.getAllWithBasicData(cuisineTypes);
            assertThat(result)
                    .containsExactlyInAnyOrderElementsOf(expectedResult)
                    .isEmpty();
        }

        @Test
        void shouldReturnAllMealsWhenCuisineTypesIsNull() {
            List<CuisineType> cuisineTypes = null;
            List<BasicMealDto> expectedResult = mockMealsByCuisineTypes(cuisineTypes);
            List<BasicMealDto> result = mealService.getAllWithBasicData(cuisineTypes);
            assertThat(result)
                    .isNotEmpty()
                    .containsExactlyInAnyOrderElementsOf(expectedResult)
                    .hasSize(5);
        }

        @Test
        void shouldHandleDuplicateCuisineTypes() {
            List<CuisineType> cuisineTypes = new ArrayList<>(List.of(CuisineType.POLISH, CuisineType.POLISH));
            List<BasicMealDto> expectedResult = mockMealsByCuisineTypes(cuisineTypes);
            List<BasicMealDto> result = mealService.getAllWithBasicData(cuisineTypes);
            assertThat(result)
                    .isNotEmpty()
                    .containsExactlyInAnyOrderElementsOf(expectedResult)
                    .hasSize(2);
        }

        private List<BasicMealDto> mockMealsByCuisineTypes (List<CuisineType> cuisineTypes) {
            List<Meal> expectedMeals = meals.stream()
                    .filter(meal -> cuisineTypes == null || cuisineTypes.contains(meal.getCuisine().getCuisineType()))
                    .distinct()
                    .toList();
            given(mealRepository.findByCuisineTypes(cuisineTypes)).willReturn(expectedMeals);
            return MealDtoMapper.mapToBasicMealDtos(expectedMeals);
        }
    }
//    @Nested
//    @DisplayName("Tests for findMealById method")
//    class FindMealById {
//        @Test
//    }
}
