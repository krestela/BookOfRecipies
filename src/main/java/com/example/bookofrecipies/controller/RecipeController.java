package com.example.bookofrecipies.controller;

import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@Service
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
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/recipe")
    public ResponseEntity<Object> createRecipeText(){
        try {
            Path path = recipeService.createRecipeText();
            if (Files.size(path) == 0){
                return ResponseEntity.noContent().build();
            }
                InputStreamResource resource1 = new InputStreamResource(new FileInputStream(path.toFile()));
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .contentLength(Files.size(path))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"AllRecipe.txt\"")
                        .body(resource1);
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    @PutMapping("/{id}")
    @Operation(description = "Редактирование списка ингредиентов")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        recipeService.editRecipe(id, recipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление ингридиента")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    @Operation(description = "Получение списка всех игредиентов")
    public Collection<Recipe> getAllRecipe() {
        return recipeService.getAllRecipe();
    }
}
