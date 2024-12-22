package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.cuisine.CuisineRepository;
import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.exception.*;
import com.example.SpringSecondAppTest.exception.custom.CuisineNotFoundException;
import com.example.SpringSecondAppTest.exception.custom.MealAlreadyExistException;
import com.example.SpringSecondAppTest.exception.custom.MealNotFoundException;
import com.example.SpringSecondAppTest.exception.custom.MealWithoutIngredientException;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.ingerdient.IngredientRepository;
import com.example.SpringSecondAppTest.meal_composition.MealComposition;
import com.example.SpringSecondAppTest.meal_composition.MealCompositionRepository;
import com.example.SpringSecondAppTest.meal_composition.dto.MealCompositionWithoutIdDto;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealWithoutIdDto;
import com.example.SpringSecondAppTest.meal.dto.MealDtoMapper;
import com.example.SpringSecondAppTest.family_meal.FamilyMealRepository;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsViewRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final CuisineRepository cuisineRepository;
    private final IngredientRepository ingredientRepository;
    private final FamilyMealRepository familyMealRepository;
    private final MealCompositionRepository mealCompositionRepository;
    private final GlobalAndFamilyMealsViewRepository globalAndFamilyMealsViewRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public List<BasicMealDto> getAllWithBasicData(List<CuisineType> cuisineTypes) {
        logger.info("Call method findMeals");
        return MealDtoMapper.mapToBasicMealDtos(
                mealRepository.findByCuisineTypes(cuisineTypes)
        );
    }

    public DetailedMealDto findMealById(Long id) {
        return MealDtoMapper.mapToDetailedMealDto(mealRepository.findWithIngredientsById(id)
                .orElseThrow(() -> new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND, id))));
    }

    @Transactional
    public DetailedMealDto createMeal(DetailedMealWithoutIdDto newMeal) {
        try {
            String nameOfNewMeal = newMeal.name();
            validateNewMealName(nameOfNewMeal);
            ensureUniqueUserMealName(nameOfNewMeal);

            Cuisine cuisine = cuisineRepository.findByCuisineType(newMeal.cuisineType())
                    .orElseThrow(() -> new CuisineNotFoundException(ErrorMessage.CUISINE_NOT_FOUND));

            Meal meal = new Meal(nameOfNewMeal, newMeal.description(), cuisine);
            Set<MealCompositionWithoutIdDto> mealCompositions = newMeal.mealCompositions();
            if (mealCompositions != null && !mealCompositions.isEmpty()) {
                List<MealComposition> newCompositions = createMealCompositions(mealCompositions);
                newCompositions.forEach(meal::addIngredientMeal);
                return MealDtoMapper.mapToDetailedMealDto(mealRepository.save(meal));
            } else {
                throw new MealWithoutIngredientException(ErrorMessage.MEAL_WITHOUT_INGREDIENT);
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_ALREADY_EXIST);
        }
    }

    @Transactional
    public DetailedMealDto updateMeal(Long mealId, DetailedMealWithoutIdDto updatedMeal) {
        try {
            Meal meal = mealRepository.findById(mealId)
                    .orElseThrow(() -> new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND, mealId)));

            String updatedMealName = updatedMeal.name();
            if (!meal.getName().equals(updatedMealName)) {
                validateNewMealName(updatedMealName);
                ensureUniqueUserMealName(updatedMealName);
                meal.setName(updatedMeal.name());
            }
            meal.setDescription(updatedMeal.description());

            Cuisine cuisine = cuisineRepository.findByCuisineType(updatedMeal.cuisineType())
                    .orElseThrow(() -> new CuisineNotFoundException(ErrorMessage.CUISINE_NOT_FOUND));
            meal.setCuisine(cuisine);

            Set<MealComposition> existingComposition = meal.getMealCompositions();
            Set<MealCompositionWithoutIdDto> newCompositions = updatedMeal.mealCompositions();
            updateExistingMealCompositions(existingComposition, newCompositions);
            if (newCompositions != null && !newCompositions.isEmpty()) {
                List<MealComposition> mealCompositions = createMealCompositions(newCompositions);
                mealCompositions.forEach(meal::addIngredientMeal);
            }
            return MealDtoMapper.mapToDetailedMealDto(mealRepository.save(meal));
        } catch (DataIntegrityViolationException e) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_ALREADY_EXIST);
        }
    }

    private void updateExistingMealCompositions(Set<MealComposition> existingCompositions, Set<MealCompositionWithoutIdDto> newCompositions) {
        List<Long> mealCompositionIdsToDelete = new ArrayList<>();
        for (MealComposition existingComposition : new HashSet<>(existingCompositions)) {
            String ingredientName = existingComposition.getIngredient().getName();
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
        mealCompositionRepository.deleteAllById(mealCompositionIdsToDelete);
    }

    public void deleteMeal(Long mealId) {
        mealRepository.deleteById(mealId);
    }

    private void validateNewMealName(String newName) {
        if (mealRepository.findMealByName(newName).isPresent()) {
            throw new MealAlreadyExistException(ErrorMessage.MEAL_NAME_ALREADY_EXIST);
        }
    }

    private void ensureUniqueUserMealName(String mealName) {
        List<GlobalAndFamilyMealsView> globalAndFamilyMealsViews = globalAndFamilyMealsViewRepository.findByName(mealName);
        globalAndFamilyMealsViews.forEach(
                view -> familyMealRepository.findById(view.getMealId()).ifPresent(
                        userMeal -> {
                            String postfix = "_" + userMeal.getFamily().getName();
                            String changedUserMealName = mealName + postfix;
                            int suffix = 1;
                            Long userId = view.getFamilyId();
                            while (familyMealRepository.findByNameAndFamilyId(changedUserMealName, userId).isPresent()) {
                                changedUserMealName = mealName + postfix + suffix++;
                            }
                            userMeal.setName(changedUserMealName);
                        }));
    }

    private List<MealComposition> createMealCompositions(Set<MealCompositionWithoutIdDto> newCompositions) {
        Map<String, Ingredient> ingredientsByNameMap = collectIngredientsByName(newCompositions);

        List<MealComposition> mealCompositions = newCompositions.stream()
                .map(ingredientsDto -> {
                    String ingredientName = ingredientsDto.ingredientName();
                    Ingredient ingredient = ingredientsByNameMap.get(ingredientName);
                    return new MealComposition(ingredientsDto.count(), ingredientsDto.unit(), ingredient);
                })
                .toList();
        return mealCompositionRepository.saveAll(mealCompositions);
    }

    private Map<String, Ingredient> collectIngredientsByName(Set<MealCompositionWithoutIdDto> mealCompositionDto) {
        List<String> ingredientsName = mealCompositionDto.stream()
                .map(MealCompositionWithoutIdDto::ingredientName)
                .toList();

        return ingredientRepository.findAllByName(ingredientsName).stream()
                .collect(Collectors.toMap(Ingredient::getName, Function.identity()));
    }
}
