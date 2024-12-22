package com.example.SpringSecondAppTest.family_ingredient;

import com.example.SpringSecondAppTest.family_ingredient.dto.FamilyIngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families/ingredients")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class FamilyIngredientController {

    private final FamilyIngredientService familyIngredientService;

    @GetMapping
    public List<IngredientDto> getIngredientsForFamily(
            @AuthenticationPrincipal User user) {
        return familyIngredientService.getIngredientsForFamily(user);
    }

    @GetMapping("/accessible")
    public List<IngredientDto> getAllAccessibleIngredients(
            @AuthenticationPrincipal User user) {
        return familyIngredientService.getAllAccessibleIngredients(user);
    }

    @GetMapping("/{id}")
    public IngredientDto getIngredient(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id) {
        return familyIngredientService.getIngredient(user, id);
    }

    @PostMapping
    public FamilyIngredientDto addFamilyIngredient(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody IngredientWithoutIdDto ingredientDetails) {
        return familyIngredientService.addFamilyIngredient(user, ingredientDetails);
    }

    @PutMapping("/{id}")
    public FamilyIngredientDto updateIngredient(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long ingredientId,
            @Valid @RequestBody IngredientWithoutIdDto ingredientDetails) {
        return familyIngredientService.updateIngredient(user, ingredientId, ingredientDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long ingredientId) {
        familyIngredientService.deleteIngredient(user, ingredientId);
    }
}
