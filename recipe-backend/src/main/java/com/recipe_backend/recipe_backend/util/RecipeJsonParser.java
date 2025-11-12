package com.recipe_backend.recipe_backend.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe_backend.recipe_backend.model.Recipe;
import com.recipe_backend.recipe_backend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class RecipeJsonParser {

    @Autowired
    private RecipeRepository recipeRepository;

    public void parseAndSaveRecipes(String jsonFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Enable parsing of NaN, Infinity, -Infinity
            objectMapper.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());

            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    Recipe recipe = new Recipe();

                    // Example fields - adapt according to your Recipe entity
                    recipe.setName(node.path("name").asText(null));
                    recipe.setDescription(node.path("description").asText(null));

                    // For numeric fields, handle NaN or missing values
                    double rating = node.path("rating").isNumber() ? node.path("rating").asDouble() : 0.0;
                    recipe.setRating((float) rating);

                    int cookTime = node.path("cookTime").isInt() ? node.path("cookTime").asInt() : 0;
                    recipe.setCookTime(cookTime);

                    // Save each recipe to the database
                    recipeRepository.save(recipe);
                }
            }

            System.out.println("Recipes parsed and saved successfully!");
        } catch (JsonParseException e) {
            System.err.println("JSON contains non-standard token (NaN/Infinity). Please check the JSON file.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
