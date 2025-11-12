package com.recipe_backend.recipe_backend;

import com.recipe_backend.recipe_backend.util.RecipeJsonParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.net.URL;

@SpringBootApplication
public class RecipeBackendApplication implements CommandLineRunner {

	private final RecipeJsonParser recipeJsonParser;

	public RecipeBackendApplication(RecipeJsonParser recipeJsonParser) {
		this.recipeJsonParser = recipeJsonParser;
	}

	public static void main(String[] args) {
		SpringApplication.run(RecipeBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Load the file from classpath
		URL resource = getClass().getClassLoader().getResource("US_recipes.json");
		if (resource == null) {
			throw new RuntimeException("recipes.json not found in resources folder!");
		}

		File file = new File(resource.toURI());
		recipeJsonParser.parseAndSaveRecipes(file.getAbsolutePath());
		System.out.println("Recipes parsed and saved successfully!");
	}
}
