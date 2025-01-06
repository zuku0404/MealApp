package com.example.SpringSecondAppTest.family_meal;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.cuisine.CuisineRepository;
import com.example.SpringSecondAppTest.exception.*;
import com.example.SpringSecondAppTest.exception.custom.*;
import com.example.SpringSecondAppTest.meal.Meal;
import com.example.SpringSecondAppTest.meal.dto.MealDtoMapper;
import com.example.SpringSecondAppTest.views.global_user_ingredients_view.GlobalAndFamilyIngredientsView;
import com.example.SpringSecondAppTest.views.global_user_ingredients_view.GlobalAndFamilyIngredientsViewRepository;
import com.example.SpringSecondAppTest.views.Source;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsViewRepository;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.ingerdient.IngredientRepository;
import com.example.SpringSecondAppTest.meal_composition.dto.MealCompositionWithoutIdDto;
import com.example.SpringSecondAppTest.meal.MealRepository;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredientRepository;
import com.example.SpringSecondAppTest.family_composition.FamilyMealComposition;
import com.example.SpringSecondAppTest.family_composition.FamilyMealCompositionRepository;
import com.example.SpringSecondAppTest.family_meal.dto.FamilyMealDtoMapper;
import com.example.SpringSecondAppTest.views.global_user_meals_view.mapper.GlobalAndFamilyMealsViewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyMealService {

    private final FamilyMealRepository familyMealRepository;
    private final MealRepository mealRepository;
    private final CuisineRepository cuisineRepository;
    private final GlobalAndFamilyIngredientsViewRepository globalAndFamilyIngredientsViewRepository;
    private final GlobalAndFamilyMealsViewRepository globalAndFamilyMealsViewRepository;
    private final FamilyMealCompositionRepository familyMealCompositionRepository;
    private final FamilyIngredientRepository familyIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public List<BasicMealDto> getMealsForFamily(User user) {
        List<GlobalAndFamilyMealsView> globalAndFamilyMealsViews = globalAndFamilyMealsViewRepository.findAllCreatedByFamily(user.getCurrentFamily().getId());
        return GlobalAndFamilyMealsViewMapper.mapToBasicMealDtos(globalAndFamilyMealsViews);
    }

    public List<BasicMealDto> getAllAccessibleMeals(User user) {
        List<GlobalAndFamilyMealsView> globalAndFamilyMealsViews = globalAndFamilyMealsViewRepository.findAllForFamily(user.getCurrentFamily().getId());
        return GlobalAndFamilyMealsViewMapper.mapToBasicMealDtos(globalAndFamilyMealsViews);
    }

    public DetailedMealDto getMeal(User user, Long id) {
        List<GlobalAndFamilyMealsView> globalAndFamilyMealsViews = globalAndFamilyMealsViewRepository.findAllForFamily(user.getCurrentFamily().getId());
        GlobalAndFamilyMealsView globalAndFamilyMealsView = globalAndFamilyMealsViews.get(Math.toIntExact(id) - 1);
        if (globalAndFamilyMealsView.getId().getSource() == Source.GLOBAL) {
            Meal meal = mealRepository.findById(globalAndFamilyMealsView.getId().getMealId())
                    .orElseThrow(() -> new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND, id)));
            return MealDtoMapper.mapToDetailedMealDto(meal);
        } else {
            FamilyMeal familyMeal = familyMealRepository.findByIdAndFamilyId(globalAndFamilyMealsView.getId().getMealId(), globalAndFamilyMealsView.getFamilyId())
                    .orElseThrow(() -> new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND, id)));
            return FamilyMealDtoMapper.mapToDetailedMealDto(familyMeal);
        }
    }

    @Transactional
    public DetailedMealDto addFamilyMeal(User user, DetailedMealWithoutIdDto meal) {
        try {
            String nameOfNewMeal = meal.name();
            Long userId = user.getId();
            validName(nameOfNewMeal, userId);

            Cuisine cuisine = cuisineRepository.findByCuisineType(meal.cuisineType())
                    .orElseThrow(() -> new CuisineNotFoundException(ErrorMessage.CUISINE_NOT_FOUND));

            Set<MealCompositionWithoutIdDto> mealCompositions = meal.mealCompositions();
            if (mealCompositions != null && !mealCompositions.isEmpty()) {
                List<FamilyMealComposition> familyMealCompositions = createFamilyMealCompositions(mealCompositions, userId);
                FamilyMeal createdMeal = new FamilyMeal(nameOfNewMeal, meal.description(), cuisine, user.getCurrentFamily());
                familyMealCompositions.forEach(createdMeal::addIngredientMeal);
                return FamilyMealDtoMapper.mapToDetailedMealDto(familyMealRepository.save(createdMeal));

            } else {
                throw new MealWithoutIngredientException(ErrorMessage.MEAL_WITHOUT_INGREDIENT);
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_ALREADY_EXIST);
        }
    }

    @Transactional
    public DetailedMealDto updateMeal(User user, Long mealId, DetailedMealWithoutIdDto updatedMeal) {
        try {
            Long userId = user.getId();
            FamilyMeal meal = familyMealRepository.findById(mealId)
                    .orElseThrow(() -> new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND_FOR_USER, mealId)));

            String updatedMealName = updatedMeal.name();
            if (!Objects.equals(meal.getName(), updatedMealName)) {
                validName(updatedMealName, userId);
                meal.setName(updatedMealName);
            }
            meal.setDescription(updatedMeal.description());

            Cuisine cuisine = cuisineRepository.findByCuisineType(updatedMeal.cuisineType())
                    .orElseThrow(() -> new CuisineNotFoundException(ErrorMessage.CUISINE_NOT_FOUND));
            meal.setCuisine(cuisine);

            Set<FamilyMealComposition> existingCompositions = meal.getFamilyMealCompositions();
            Set<MealCompositionWithoutIdDto> newCompositions = updatedMeal.mealCompositions();
            updateExistingUserMealCompositions(existingCompositions, newCompositions);
            if (newCompositions != null && !newCompositions.isEmpty()) {
                List<FamilyMealComposition> familyMealCompositions = createFamilyMealCompositions(newCompositions, userId);
                familyMealCompositions.forEach(meal::addIngredientMeal);
            }
            return FamilyMealDtoMapper.mapToDetailedMealDto(familyMealRepository.save(meal));
        } catch (
                DataIntegrityViolationException e) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_ALREADY_EXIST);
        }
    }

    public void deleteMeal(User user, Long mealId) {
        familyMealRepository.findByIdAndFamilyId(mealId, user.getId())
                .ifPresent(familyMealRepository::delete);
    }

    private void validName(String nameOfNewMeal, Long userId) {
        if (globalAndFamilyMealsViewRepository.findByNameAndAvailableForFamilyId(nameOfNewMeal, userId).isPresent()) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_NAME_ALREADY_EXIST);
        }
    }

    private List<FamilyMealComposition> createFamilyMealCompositions(Set<MealCompositionWithoutIdDto> userIngredientMealsDto,
                                                                     Long familyId) {

        List<GlobalAndFamilyIngredientsView> globalAndFamilyIngredientsViews = validAndGetGlobalAndUserIngredientsView(
                userIngredientMealsDto, familyId);
        Map<Source, List<Long>> ingredientIdsForDifferentSources = groupIngredientsBySource(globalAndFamilyIngredientsViews);
        List<FamilyIngredient> familyIngredients = getUserIngredientsFromRepo(ingredientIdsForDifferentSources);
        List<Ingredient> globalIngredients = getGlobalIngredientsFromRepo(ingredientIdsForDifferentSources);

        List<FamilyMealComposition> familyMealCompositions = new ArrayList<>();
        userIngredientMealsDto.forEach(ingredientMeals -> {
            Optional<FamilyIngredient> userIngredientOptional = getUserIngredient(familyIngredients, ingredientMeals.ingredientName());
            if (userIngredientOptional.isPresent()) {
                FamilyIngredient familyIngredient = userIngredientOptional.get();
                FamilyMealComposition familyMealComposition = new FamilyMealComposition(ingredientMeals.count(), ingredientMeals.unit(), familyIngredient);
                familyMealCompositions.add(familyMealComposition);
            } else {
                Ingredient globalIngredient = getGlobalIngredient(globalIngredients, ingredientMeals.ingredientName()).get();
                FamilyMealComposition familyMealComposition = new FamilyMealComposition(ingredientMeals.count(), ingredientMeals.unit(), globalIngredient);
                familyMealCompositions.add(familyMealComposition);
            }
        });
        return familyMealCompositionRepository.saveAll(familyMealCompositions);
    }

    private List<GlobalAndFamilyIngredientsView> validAndGetGlobalAndUserIngredientsView(Set<MealCompositionWithoutIdDto> mealUserIngredientMeals, Long userId) {
        List<String> ingredientsName = getAllIngredientsName(mealUserIngredientMeals);
        List<GlobalAndFamilyIngredientsView> ingredients =
                globalAndFamilyIngredientsViewRepository.findAllByName(ingredientsName);
        validIngredientsNamesForUser(ingredients, userId);
        return ingredients;
    }

    private Map<Source, List<Long>> groupIngredientsBySource(List<GlobalAndFamilyIngredientsView> ingredients) {
        return ingredients.stream()
                .collect(Collectors.groupingBy(
                        GlobalAndFamilyIngredientsView::getSource,
                        Collectors.mapping(GlobalAndFamilyIngredientsView::getId, Collectors.toList())
                ));
    }

    private List<String> getAllIngredientsName(Set<MealCompositionWithoutIdDto> mealUserIngredientMeals) {
        return mealUserIngredientMeals.stream()
                .map(MealCompositionWithoutIdDto::ingredientName)
                .toList();
    }

    private void validIngredientsNamesForUser(List<GlobalAndFamilyIngredientsView> ingredients, Long familyId) {
        Optional<GlobalAndFamilyIngredientsView> ingredientWhichNotExistForUser = ingredients.stream()
                .filter(ingredientsView -> ingredientsView.getSource() != Source.GLOBAL && ingredientsView.getFamilyId().equals(familyId))
                .findFirst();

        if (ingredientWhichNotExistForUser.isPresent()) {
            throw new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_WITH_NAME_NOT_EXIST_FOR_USER,
                    ingredientWhichNotExistForUser.get().getName()));
        }
    }

    public List<FamilyIngredient> getUserIngredientsFromRepo(Map<Source, List<Long>> ingredientIdsForDifferentSources) {
        List<Long> ingredientIds = ingredientIdsForDifferentSources.get(Source.CUSTOM);
        return ingredientIds != null ? familyIngredientRepository.findAllById(ingredientIds) : new ArrayList<>();
    }

    public List<Ingredient> getGlobalIngredientsFromRepo(Map<Source, List<Long>> ingredientIdsForDifferentSources) {
        List<Long> ingredientIds = ingredientIdsForDifferentSources.get(Source.GLOBAL);
        return ingredientIds != null ? ingredientRepository.findAllById(ingredientIds) : new ArrayList<>();
    }

    private void updateExistingUserMealCompositions(Set<FamilyMealComposition> existingCompositions, Set<MealCompositionWithoutIdDto> newCompositions) {
        List<Long> mealCompositionIdsToDelete = new ArrayList<>();
        for (FamilyMealComposition existingComposition : new HashSet<>(existingCompositions)) {
            String ingredientName = existingComposition.getIngredient() != null ?
                    existingComposition.getIngredient().getName() : existingComposition.getFamilyIngredient().getName();
            Optional<MealCompositionWithoutIdDto> matchingNewComposition = newCompositions.stream()
                    .filter(newComposition -> newComposition.ingredientName().equalsIgnoreCase(ingredientName))
                    .findFirst();
            matchingNewComposition.ifPresentOrElse(
                    newComposition -> {
                        existingComposition.setUnit(newComposition.unit());
                        existingComposition.setCount(newComposition.count());
                        newCompositions.remove(newComposition);
                    },
                    () -> {
                        existingCompositions.remove(existingComposition);
                        mealCompositionIdsToDelete.add(existingComposition.getId());
                    }
            );
        }
        familyMealCompositionRepository.deleteAllById(mealCompositionIdsToDelete);
    }

    private Optional<FamilyIngredient> getUserIngredient(List<FamilyIngredient> familyIngredients, String ingredientName) {
        return familyIngredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(ingredientName))
                .findFirst();
    }

    private Optional<Ingredient> getGlobalIngredient(List<Ingredient> globalIngredients, String ingredientName) {
        return globalIngredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(ingredientName))
                .findFirst();
    }


}