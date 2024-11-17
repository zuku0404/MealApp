package com.example.SpringSecondAppTest.user_meal;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.cuisine.CuisineRepository;
import com.example.SpringSecondAppTest.exception.*;
import com.example.SpringSecondAppTest.global_user_ingredients_view.GlobalAndUserIngredientsView;
import com.example.SpringSecondAppTest.global_user_ingredients_view.GlobalAndUserIngredientsViewRepository;
import com.example.SpringSecondAppTest.global_user_ingredients_view.Source;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.ingerdient.IngredientRepository;
import com.example.SpringSecondAppTest.ingredient_meal.dto.IngredientsMealWithoutIdDto;
import com.example.SpringSecondAppTest.meal.MealRepository;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_ingredient.UserIngredient;
import com.example.SpringSecondAppTest.user_ingredient.UserIngredientRepository;
import com.example.SpringSecondAppTest.user_ingredient_meal.UserIngredientMeal;
import com.example.SpringSecondAppTest.user_ingredient_meal.UserIngredientMealRepository;
import com.example.SpringSecondAppTest.user_meal.dto.DetailedUserMealDto;
import com.example.SpringSecondAppTest.user_meal.dto.UserMealDtoMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMealService {

    private final UserMealRepository userMealRepository;
    private final MealRepository mealRepository;
    private final CuisineRepository cuisineRepository;
    private final GlobalAndUserIngredientsViewRepository globalAndUserIngredientsViewRepository;
    private final UserIngredientMealRepository userIngredientMealRepository;
    private final UserIngredientRepository userIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public List<DetailedUserMealDto> findAllDetailed(User user) {
        return UserMealDtoMapper.mapToDetailedMealDtos(
                userMealRepository.findAllForUserByUserLogin(user.getId()));
    }

    public List<BasicMealDto> findAllBasic(User user) {
        return UserMealDtoMapper.mapToBasicMealDtos(
                userMealRepository.findAllWithBasic(user.getId()));
    }

    public DetailedUserMealDto findById(User user, Long id) {
        UserMeal meal = userMealRepository.findById(id)
                .orElseThrow(() -> new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND, id, "user")));
        if (!Objects.equals(meal.getUser().getId(), user.getId())) {
            throw new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND_FOR_USER, id));
        }
        return UserMealDtoMapper.mapToDetailedMealDto(meal);
    }

    @Transactional
    public DetailedUserMealDto create(User user, DetailedMealWithoutIdDto newMeal) {
        try {
            String nameOfNewMeal = newMeal.name();
            validateUniqueMealName(nameOfNewMeal, user.getId());

            Cuisine cuisine = cuisineRepository.findByCuisineType(newMeal.cuisineType())
                    .orElseThrow(() -> new CuisineNotFoundException(ErrorMessage.CUISINE_NOT_FOUND));

            UserMeal meal = new UserMeal(nameOfNewMeal, newMeal.description(), cuisine, user);
            Set<IngredientsMealWithoutIdDto> ingredientsMeals = newMeal.ingredientMeals();
            List<UserIngredientMeal> userIngredientMeals = addNewMealIngredients(ingredientsMeals, newMeal);
            userIngredientMeals.forEach(meal::addIngredientMeal);
            return UserMealDtoMapper.mapToDetailedMealDto(userMealRepository.save(meal));
        } catch (
                DataIntegrityViolationException e) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_ALREADY_EXIST);
        }
    }

    @Transactional
    public DetailedUserMealDto update(User user, Long mealId, DetailedMealWithoutIdDto updatedMeal) {
        try {
            UserMeal oldMeal = userMealRepository.findById(mealId)
                    .orElseThrow(() -> new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND_FOR_USER, mealId)));

            String updatedMealName = updatedMeal.name();
            if (!Objects.equals(oldMeal.getName(), updatedMealName)) {
                validateUniqueMealName(updatedMealName, user.getId());
                oldMeal.setName(updatedMealName);
            }
            oldMeal.setDescription(updatedMeal.description());

            Cuisine cuisine = cuisineRepository.findByCuisineType(updatedMeal.cuisineType())
                    .orElseThrow(() -> new CuisineNotFoundException(ErrorMessage.CUISINE_NOT_FOUND));
            oldMeal.setCuisine(cuisine);

            Set<UserIngredientMeal> oldMealUserIngredientMeals = oldMeal.getUserIngredientMeals();
            Set<IngredientsMealWithoutIdDto> updatedMealUserIngredientMeals = updatedMeal.ingredientMeals();
            updateAndRemoveMealIngredients(oldMealUserIngredientMeals, updatedMealUserIngredientMeals);
            if (!updatedMealUserIngredientMeals.isEmpty()) {
                List<UserIngredientMeal> userIngredientMeals = addNewMealIngredients(updatedMealUserIngredientMeals, updatedMeal);
                userIngredientMeals.forEach(oldMeal::addIngredientMeal);
            }
            return UserMealDtoMapper.mapToDetailedMealDto(userMealRepository.save(oldMeal));
        } catch (
                DataIntegrityViolationException e) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_ALREADY_EXIST);
        }
    }

    @Transactional
    public void delete(User user, Long mealId) {
        userMealRepository.findByIdAndUserId(mealId, user.getId())
                .ifPresent(userMealRepository::delete);
    }

    private List<UserIngredientMeal> addNewMealIngredients(Set<IngredientsMealWithoutIdDto> mealUserIngredientMealsToAdd, DetailedMealWithoutIdDto meal) {
        if (mealUserIngredientMealsToAdd == null || mealUserIngredientMealsToAdd.isEmpty()) {
            throw new MealWithoutIngredientException(ErrorMessage.MEAL_WITHOUT_INGREDIENT);
        }

        List<String> ingredientsNameToAdd = mealUserIngredientMealsToAdd.stream()
                .map(IngredientsMealWithoutIdDto::ingredientName)
                .toList();

        List<GlobalAndUserIngredientsView> ingredients =
                globalAndUserIngredientsViewRepository.findAllByName(ingredientsNameToAdd);

        Map<Source, List<Long>> ingredientIdsForDifferentSources = ingredients.stream()
                .collect(Collectors.groupingBy(
                        GlobalAndUserIngredientsView::getSource,
                        Collectors.mapping(GlobalAndUserIngredientsView::getId, Collectors.toList())
                ));

        List<UserIngredient> userIngredients = ingredientIdsForDifferentSources.get(Source.USER) != null ?
                userIngredientRepository.findAllById(ingredientIdsForDifferentSources.get(Source.USER)) : new ArrayList<>();

        List<Ingredient> globalIngredients = ingredientIdsForDifferentSources.get(Source.GLOBAL) != null ?
                ingredientRepository.findAllById(ingredientIdsForDifferentSources.get(Source.GLOBAL)) : new ArrayList<>();

        List<UserIngredientMeal> userIngredientMeals = new ArrayList<>();
        meal.ingredientMeals().forEach(ingredientMeals -> {
            Optional<UserIngredient> userIngredientOptional = findUserIngredient(userIngredients, ingredientMeals.ingredientName());
            if (userIngredientOptional.isPresent()) {
                UserIngredient userIngredient = userIngredientOptional.get();
                UserIngredientMeal userIngredientMeal = new UserIngredientMeal(ingredientMeals.count(), ingredientMeals.unit(), userIngredient);
                userIngredientMeals.add(userIngredientMeal);
            } else {
                Optional<Ingredient> globalIngredientOptional = findGlobalIngredient(globalIngredients, ingredientMeals.ingredientName());
                if (globalIngredientOptional.isPresent()) {
                    Ingredient ingredient = globalIngredientOptional.get();
                    UserIngredientMeal userIngredientMeal = new UserIngredientMeal(ingredientMeals.count(), ingredientMeals.unit(), ingredient);
                    userIngredientMeals.add(userIngredientMeal);
                } else {
                    throw new IngredientNotFoundException(
                            String.format(ErrorMessage.INGREDIENT_WITH_NAME_FOUND, ingredientMeals.ingredientName()));
                }
            }
        });
        userIngredientMealRepository.saveAll(userIngredientMeals);
        return userIngredientMeals;
    }

    private void updateAndRemoveMealIngredients(Set<UserIngredientMeal> oldMealUserIngredientMeals, Set<IngredientsMealWithoutIdDto> updatedMealUserIngredientMeals) {
        for (UserIngredientMeal oldMealUserIngredientMeal : new HashSet<>(oldMealUserIngredientMeals)) {
            for (IngredientsMealWithoutIdDto updatedMealUserIngredientMeal : new HashSet<>(updatedMealUserIngredientMeals)) {
                String oldMealIngredientName = oldMealUserIngredientMeal.getIngredient() != null ?
                        oldMealUserIngredientMeal.getIngredient().getName() : oldMealUserIngredientMeal.getUserMeal().getName();
                String updatedMealIngredientName = updatedMealUserIngredientMeal.ingredientName();
                if (Objects.equals(oldMealIngredientName, updatedMealIngredientName)) {
                    oldMealUserIngredientMeal.setUnit(updatedMealUserIngredientMeal.unit());
                    oldMealUserIngredientMeal.setCount(updatedMealUserIngredientMeal.count());
                    updatedMealUserIngredientMeals.remove(updatedMealUserIngredientMeal);
                } else {
                    oldMealUserIngredientMeals.remove(oldMealUserIngredientMeal);
                }
            }
        }
    }

    private void validateUniqueMealName(String newMealName, Long userId) {
        if (userMealRepository.findByNameAndUserId(newMealName, userId).isPresent() ||
                mealRepository.findMealByName(newMealName).isPresent()) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_NAME_ALREADY_EXIST);
        }
    }

    private Optional<UserIngredient> findUserIngredient(List<UserIngredient> userIngredients, String ingredientName) {
        return userIngredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(ingredientName))
                .findFirst();
    }

    private Optional<Ingredient> findGlobalIngredient(List<Ingredient> globalIngredients, String ingredientName) {
        return globalIngredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(ingredientName))
                .findFirst();
    }
}
