package com.recipe_backend.recipe_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Recipe API is running! Use /api/recipes or /api/recipes/search";
    }
}
