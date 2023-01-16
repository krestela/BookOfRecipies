package com.example.bookofrecipies.controller;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/newrecipe")
    public void addNewRecipe(@RequestParam Recipe recipe) {
        recipeService.addNewRecipe(recipe);
    }

    @GetMapping("/getrecipe")
    public void getRecipe(@RequestParam int numberId) {
        recipeService.getRecipe(numberId);
    }

    @GetMapping("/addingr")
    public void addIngredient(@RequestParam Ingridients ingridients) {
        recipeService.addNewIngredient(ingridients);
    }

    @GetMapping("/getingr")
    public void getIngredient(@RequestParam int numberId) {
        recipeService.getIngredient(numberId);
    }


}
