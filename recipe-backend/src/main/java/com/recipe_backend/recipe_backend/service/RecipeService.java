package com.recipe_backend.recipe_backend.service;

import com.recipe_backend.recipe_backend.model.Recipe;
import com.recipe_backend.recipe_backend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    // Save recipe
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    // Save multiple recipes
    public List<Recipe> saveAllRecipes(List<Recipe> recipes) {
        return recipeRepository.saveAll(recipes);
    }

    // Get all recipes
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Get recipe by ID
    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    // Update recipe
    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(recipe -> {
            recipe.setTitle(updatedRecipe.getTitle());
            recipe.setCuisine(updatedRecipe.getCuisine());
            recipe.setRating(updatedRecipe.getRating());
            recipe.setPrepTime(updatedRecipe.getPrepTime());
            recipe.setCookTime(updatedRecipe.getCookTime());
            recipe.setTotalTime(updatedRecipe.getTotalTime());
            recipe.setDescription(updatedRecipe.getDescription());
            recipe.setNutrients(updatedRecipe.getNutrients());
            recipe.setServes(updatedRecipe.getServes());
            return recipeRepository.save(recipe);
        }).orElseThrow(() -> new RuntimeException("Recipe not found with id " + id));
    }

    // Delete recipe
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
