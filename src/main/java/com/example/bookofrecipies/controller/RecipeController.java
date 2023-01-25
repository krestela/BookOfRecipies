package com.example.bookofrecipies.controller;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/newRecipe")
    public ResponseEntity<Long> addNewRecipe(@RequestBody Recipe recipe) {
        long id = recipeService.addNewRecipe(recipe);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe>getRecipe(@PathVariable long id) {
        Recipe recipe= recipeService.getRecipe(id);
        if (recipe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }


}
