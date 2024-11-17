package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.cuisine.CuisineRepository;
import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.exception.*;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.ingerdient.IngredientRepository;
import com.example.SpringSecondAppTest.ingredient_meal.IngredientMeal;
import com.example.SpringSecondAppTest.ingredient_meal.IngredientMealRepository;
import com.example.SpringSecondAppTest.ingredient_meal.dto.IngredientsMealWithoutIdDto;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealWithoutIdDto;
import com.example.SpringSecondAppTest.meal.dto.MealDtoMapper;
import com.example.SpringSecondAppTest.user.Role;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_meal.UserMeal;
import com.example.SpringSecondAppTest.user_meal.UserMealRepository;
import com.example.SpringSecondAppTest.user_meal.UserMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private static final int PAGE_SIZE = 10;
    private final CuisineRepository cuisineRepository;
    private final IngredientRepository ingredientRepository;
    private final UserMealRepository userMealRepository;
    private final IngredientMealRepository ingredientMealRepository;
    private final UserMealService userMealService;

    public List<DetailedMealDto> findAllDetailed() {
        return MealDtoMapper.mapToDetailedMealDtos(mealRepository.findAllWithDetails());
    }

    public List<BasicMealDto> findAllBasic(List<CuisineType> cuisineTypes, Integer page) {
        int currentPage = page != null && page > 0 ? page - 1 : 0;
        return MealDtoMapper.mapToBasicMealDtos(
                mealRepository.findByCuisineTypes(cuisineTypes, PageRequest.of(currentPage, PAGE_SIZE))
        );
    }

    public DetailedMealDto findById(Long id) {
        return MealDtoMapper.mapToDetailedMealDto(mealRepository.findWithIngredientsById(id)
                .orElseThrow(() -> new NoSuchElementException("meal not exist")));
    }

    @Transactional
    public DetailedMealDto saveMeal(DetailedMealWithoutIdDto newMeal) {
        String mealName = newMeal.name();
        try {
            validateNewMealName(mealName);
            ensureUniqueUserMealName(mealName);

            Cuisine cuisine = cuisineRepository.findByCuisineType(newMeal.cuisineType())
                    .orElseThrow(() -> new CuisineNotFoundException(ErrorMessage.CUISINE_NOT_FOUND));

            Meal meal = new Meal(mealName, newMeal.description(), cuisine);

            List<String> ingredientsNames = newMeal.ingredientMeals().stream()
                    .map(IngredientsMealWithoutIdDto::ingredientName)
                    .toList();

            Map<String, Ingredient> ingredientCollect = ingredientRepository.findAllByName(ingredientsNames).stream()
                    .collect(Collectors.toMap(Ingredient::getName, Function.identity()));

            List<IngredientMeal> ingredientMeals = newMeal.ingredientMeals().stream()
                    .map(ingredientsDto -> {
                        String ingredientName = ingredientsDto.ingredientName();
                        Ingredient ingredient = ingredientCollect.get(ingredientName);
                        if (ingredient != null) {
                            IngredientMeal ingredientMeal = new IngredientMeal(ingredientsDto.count(), ingredientsDto.unit(), ingredient);
                            meal.addIngredientMeal(ingredientMeal);
                            return ingredientMeal;
                        } else {
                            throw new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_WITH_NAME_FOUND, ingredientName));
                        }
                    })
                    .toList();
            ingredientMealRepository.saveAll(ingredientMeals);
            return MealDtoMapper.mapToDetailedMealDto(mealRepository.save(meal));
        } catch (DataIntegrityViolationException e) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_ALREADY_EXIST);
        }
    }

    @Transactional
    public DetailedMealDto update(Long mealId, DetailedMealWithoutIdDto updatedMeal) {
        try {
            Meal meal = mealRepository.findById(mealId)
                    .orElseThrow(() -> new MealNotFoundException(ErrorMessage.MEAL_NOT_FOUND));

            String updatedMealName = updatedMeal.name();
            if (!meal.getName().equals(updatedMealName)) {
                validateNewMealName(updatedMealName);
                ensureUniqueUserMealName(updatedMealName);
            }

            Cuisine cuisine = cuisineRepository.findByCuisineType(updatedMeal.cuisineType())
                    .orElseThrow(() -> new NoSuchElementException("cuisine not exist"));

            meal.setName(updatedMeal.name());
            meal.setDescription(updatedMeal.description());
            meal.setCuisine(cuisine);

            List<String> ingredientsNames = updatedMeal.ingredientMeals().stream()
                    .map(IngredientsMealWithoutIdDto::ingredientName)
                    .toList();

            Map<String, IngredientMeal> existingIngredientMeals = meal.getIngredientMeals().stream()
                    .collect(Collectors.toMap(ingredientMeal -> ingredientMeal.getIngredient().getName(), Function.identity()));

            Map<String, Ingredient> existingIngredient = ingredientRepository.findAllByName(ingredientsNames).stream()
                    .collect(Collectors.toMap(Ingredient::getName, Function.identity()));

            updatedMeal.ingredientMeals().forEach(ingredientsDto -> {
                IngredientMeal ingredientMeal = existingIngredientMeals.remove(ingredientsDto.ingredientName());
                Ingredient ingredient = existingIngredient.get(ingredientsDto.ingredientName());
                if (ingredient == null) {
                    throw new IngredientNotFoundException(
                            String.format(ErrorMessage.INGREDIENT_WITH_NAME_FOUND, ingredientsDto.ingredientName()));
                }
                if (ingredientMeal != null) {
                    ingredientMeal.setCount(ingredientsDto.count());
                    ingredientMeal.setUnit(ingredientsDto.unit());
                    ingredientMeal.setIngredient(ingredient);
                } else {
                    meal.addIngredientMeal(new IngredientMeal(ingredientsDto.count(), ingredientsDto.unit(), ingredient));
                }
            });

            ingredientMealRepository.deleteAll(existingIngredientMeals.values());
        // delete all meal_ingredient which weren't use

            return MealDtoMapper.mapToDetailedMealDto(mealRepository.save(meal));
        } catch (DataIntegrityViolationException e) {
            throw new MealAlreadyExistException("Meal with this name already exists");
        }
    }

    private void validateNewMealName(String newName) {
        if (mealRepository.findMealByName(newName).isPresent()) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_NAME_ALREADY_EXIST);
        }
    }

    private void ensureUniqueUserMealName(String mealName) {
        userMealRepository.findByName(mealName).forEach(userMeal -> {
            String newUserMealName = mealName + "_user";
            int suffix = 1;
            Long userId = userMeal.getUser().getId();
            while (userMealRepository.findByNameAndUserId(newUserMealName, userId).isPresent()) {
                newUserMealName = mealName + "_user" + suffix++;
            }
            userMeal.setName(newUserMealName);
        });
    }

    public void delete(Long mealId) {
        mealRepository.deleteById(mealId);
    }

    public Integer getTotalNumberOfPages(int pageSize) {
        return (int) Math.ceil((double) mealRepository.count() / pageSize);
    }
}
