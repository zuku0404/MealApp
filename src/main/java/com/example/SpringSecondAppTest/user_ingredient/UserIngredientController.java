package com.example.SpringSecondAppTest.user_ingredient;

import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_ingredient.dto.UserIngredientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/ingredients")
@RequiredArgsConstructor
public class UserIngredientController {

    private final UserIngredientService userIngredientService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<IngredientDto> getIngredient(
            @AuthenticationPrincipal User user) {
        return userIngredientService.findIngredientsForUser(user);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public List<IngredientDto> findAllIngredientsForUser(
            @AuthenticationPrincipal User user) {
        return userIngredientService.findIngredientsForUserWithMainIngredients(user);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public UserIngredientDto createIngredient(
            @AuthenticationPrincipal User user,
            @RequestBody IngredientWithoutIdDto newIngredient) {
        return userIngredientService.createIngredientForUser(user, newIngredient);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public UserIngredientDto updateIngredient(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long ingredientId,
            @RequestBody IngredientWithoutIdDto updatedIngredient) {
        return userIngredientService.updateIngredientForUser(user, ingredientId, updatedIngredient);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteIngredient(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long ingredientId) {
        userIngredientService.deleteIngredientForUser(user, ingredientId);
    }
}
