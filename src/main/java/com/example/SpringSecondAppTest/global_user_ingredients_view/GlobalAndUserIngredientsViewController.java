package com.example.SpringSecondAppTest.global_user_ingredients_view;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/all")
public class GlobalAndUserIngredientsViewController {

    private final GlobalAndUserIngredientsViewService globalAndUserIngredientsViewService;

    @GetMapping
    @PreAuthorize("hasRole'USER'")
    public List<GlobalAndUserIngredientsView> getIngredients(Authentication authentication){
        return globalAndUserIngredientsViewService.findAllForUser(authentication);
    }
}
