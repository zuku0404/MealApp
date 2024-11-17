package com.example.SpringSecondAppTest.ingerdient;

import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientWithoutIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public List<IngredientDto> findAll(
            @RequestParam (name = "categories", required = false) List<IngredientCategory> ingredientCategory) {
        return ingredientService.findAll(ingredientCategory);
    }

    @GetMapping("/{id}")
    public IngredientDto getIngredient(@PathVariable("id") Long id) {
        return ingredientService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public IngredientDto createIngredient(@RequestBody IngredientWithoutIdDto ingredient) {
        return ingredientService.save(ingredient);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public IngredientDto updateIngredient(@PathVariable("id") Long id, @RequestBody IngredientWithoutIdDto ingredientDto) {
        return ingredientService.update(id, ingredientDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteIngredient(@PathVariable("id") Long id) {
        ingredientService.delete(id);
    }
}
