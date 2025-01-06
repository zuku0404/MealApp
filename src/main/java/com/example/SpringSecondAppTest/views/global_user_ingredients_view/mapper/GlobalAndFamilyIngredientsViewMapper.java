package com.example.SpringSecondAppTest.views.global_user_ingredients_view.mapper;

import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.views.global_user_ingredients_view.GlobalAndFamilyIngredientsView;

import java.util.List;

public class GlobalAndFamilyIngredientsViewMapper {
    private GlobalAndFamilyIngredientsViewMapper(){}

    public static List<IngredientDto> mapToIngredientsDtos(List<GlobalAndFamilyIngredientsView> globalAndFamilyIngredientsViews){
        return globalAndFamilyIngredientsViews.stream()
                .map(GlobalAndFamilyIngredientsViewMapper::mapToIngredientDto)
                .toList();
    }

    public static IngredientDto mapToIngredientDto(GlobalAndFamilyIngredientsView globalAndFamilyIngredient){
        return new IngredientDto(
                globalAndFamilyIngredient.getRowId(),
                globalAndFamilyIngredient.getName(),
                globalAndFamilyIngredient.getSource(),
                globalAndFamilyIngredient.getIngredientCategory()
        );
    }
}