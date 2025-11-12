package com.recipe_backend.recipe_backend.controller;

import com.recipe_backend.recipe_backend.model.Recipe;
import com.recipe_backend.recipe_backend.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // 🔹 Add a recipe
    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.saveRecipe(recipe);
    }

    // 🔹 Get all recipes
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    // 🔹 Get recipe by id
    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id " + id));
    }

    // 🔹 Update recipe
    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe recipeDetails) {
        return recipeService.updateRecipe(id, recipeDetails);
    }

    // 🔹 Delete recipe
    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "Recipe deleted successfully with id " + id;
    }
}
