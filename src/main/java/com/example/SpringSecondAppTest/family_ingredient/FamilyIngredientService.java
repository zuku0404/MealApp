package com.example.SpringSecondAppTest.family_ingredient;

import com.example.SpringSecondAppTest.exception.ErrorMessage;
import com.example.SpringSecondAppTest.exception.custom.IngredientAlreadyExistException;
import com.example.SpringSecondAppTest.exception.custom.IngredientNotFoundException;
import com.example.SpringSecondAppTest.family_ingredient.dto.FamilyIngredientDto;
import com.example.SpringSecondAppTest.family_ingredient.dto.FamilyIngredientDtoMapper;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.ingerdient.IngredientRepository;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDtoMapper;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.views.Source;
import com.example.SpringSecondAppTest.views.global_user_ingredients_view.GlobalAndFamilyIngredientsView;
import com.example.SpringSecondAppTest.views.global_user_ingredients_view.GlobalAndFamilyIngredientsViewRepository;
import com.example.SpringSecondAppTest.views.global_user_ingredients_view.mapper.GlobalAndFamilyIngredientsViewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FamilyIngredientService {
    private final FamilyIngredientRepository familyIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final GlobalAndFamilyIngredientsViewRepository globalAndFamilyIngredientsViewRepository;

    public List<IngredientDto> getIngredientsForFamily(User user) {
        Long familyId = user.getCurrentFamily().getId();
        List<FamilyIngredient> ingredients = familyIngredientRepository.findAllForCurrentFamily(familyId);
        return FamilyIngredientDtoMapper.mapToIngredientsMealsDtos(ingredients);
    }

    public List<IngredientDto> getAllAccessibleIngredients(User user) {
        List<GlobalAndFamilyIngredientsView> globalAndFamilyIngredientsViews =
                globalAndFamilyIngredientsViewRepository.findAllCreatedByFamily(user.getCurrentFamily().getId());
        return GlobalAndFamilyIngredientsViewMapper.mapToIngredientsDtos(globalAndFamilyIngredientsViews);
    }

    public IngredientDto getIngredient(User user, Long id) {
        GlobalAndFamilyIngredientsView ingredientsView = globalAndFamilyIngredientsViewRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_NOT_FOUND, id)));
        if (ingredientsView.getSource() == Source.GLOBAL) {
            Ingredient ingredient = ingredientRepository.findById(id)
                    .orElseThrow(() -> new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_NOT_FOUND, id)));
            return IngredientDtoMapper.mapToIngredientDto(ingredient);
        } else if (!Objects.equals(ingredientsView.getFamilyId(), user.getCurrentFamily().getId())) {
            FamilyIngredient familyIngredient = familyIngredientRepository.findById(id)
                    .orElseThrow(() -> new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_NOT_FOUND, id)));
            return FamilyIngredientDtoMapper.mapToIngredientsMealsDto(familyIngredient);
        } else {
            throw new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_WITH_NAME_NOT_EXIST_FOR_USER, user.getId()));
        }
    }

    @Transactional
    public FamilyIngredientDto addFamilyIngredient(User user, IngredientWithoutIdDto newIngredient) {
        validateIngredientUniqueness(user, newIngredient.name());
        FamilyIngredient familyIngredient = FamilyIngredientDtoMapper.mapToFamilyIngredient(newIngredient, user.getCurrentFamily());
        return FamilyIngredientDtoMapper.mapToUserIngredientDto(familyIngredientRepository.save(familyIngredient));
    }

    @Transactional
    public FamilyIngredientDto updateIngredient(User user, Long ingredientId, IngredientWithoutIdDto updatedIngredient) {
        validateIngredientUniqueness(user, updatedIngredient.name(), ingredientId);

        FamilyIngredient ingredient = familyIngredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_NOT_FOUND, ingredientId)));

        ingredient.setName(updatedIngredient.name());
        ingredient.setIngredientCategory(updatedIngredient.ingredientCategory());

        return FamilyIngredientDtoMapper.mapToUserIngredientDto(familyIngredientRepository.save(ingredient));
    }

    @Transactional
    public void deleteIngredient(User user, Long ingredientId) {
        Long familyId = user.getCurrentFamily().getId();
        List<FamilyIngredient> ingredients = familyIngredientRepository.findAllForCurrentFamily(familyId);
        boolean ingredientExists = ingredients.stream()
                .anyMatch(ingredient -> ingredient.getId().equals(ingredientId));
        if (!ingredientExists) {
            throw new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_NOT_FOUND, ingredientId));
        }
        familyIngredientRepository.deleteById(ingredientId);
    }

    private void validateIngredientUniqueness(User user, String ingredientName) {
        validateIngredientUniqueness(user, ingredientName, null);
    }

    private void validateIngredientUniqueness(User user, String ingredientName, Long excludeIngredientId) {
        Long familyId = user.getCurrentFamily().getId();
        boolean ingredientExistsInFamily = familyIngredientRepository.findAllForCurrentFamily(familyId).stream()
                .anyMatch(ingredient -> ingredient.getName().equalsIgnoreCase(ingredientName)
                        && (!ingredient.getId().equals(excludeIngredientId)));

        boolean ingredientExistsGlobally = ingredientRepository.findByName(ingredientName).isPresent();

        if (ingredientExistsInFamily || ingredientExistsGlobally) {
            throw new IngredientAlreadyExistException(ErrorMessage.INGREDIENT_ALREADY_EXIST);
        }
    }
}
