package com.example.bookofrecipies.controller;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепт", description = "Рецепты и методы с ними")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/newRecipe")
    @Operation(description = "Добавление нового рецепта")
    public ResponseEntity<Long> addNewRecipe(@RequestBody Recipe recipe) {
        long id = recipeService.addNewRecipe(recipe);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получения рецепта по id")
    public ResponseEntity<Recipe>getRecipe(@PathVariable long id) {
        Recipe recipe= recipeService.getRecipe(id);
        if (recipe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @PutMapping("/{id}")
    @Operation(description = "Редактирование списка ингредиентов")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        recipeService.editRecipe(id, recipe);
        if (recipe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @DeleteMapping("/{id}")
    @Operation(description = "Удаление ингридиента")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        if (recipeService.deleteRecipe(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/all")
    @Operation(description = "Получение списка всех игредиентов")
    public void getAllRecipe() {
        recipeService.getAllRecipe();
    }


}
