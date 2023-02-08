package com.example.bookofrecipies.controller;

import com.example.bookofrecipies.model.Ingredients;
import com.example.bookofrecipies.service.IngredientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингридиенты", description = "Ингридиенты и методы с ними")
public class IngredientsController {
    private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }
    @PostMapping("/addingr")
    @Operation(description = "Добавление нового ингридиента")
    public ResponseEntity<Long> addIngredient(@RequestBody Ingredients ingredients) {
        long id = ingredientsService.addNewIngredient(ingredients);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получения ингридиента по id")
    public ResponseEntity<Ingredients> getIngredient(@PathVariable long id) {
        Ingredients ingredients = ingredientsService.getIngredient(id);
        if (ingredients == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @PutMapping("/{id}")
    @Operation(description = "Редактирование списка ингредиентов")
    public ResponseEntity<Ingredients> editIngredients(@PathVariable long id, @RequestBody Ingredients ingredients) {
        ingredientsService.getIngredient(id);
        if (ingredients == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }
    @DeleteMapping("/{id}")
    @Operation(description = "Удаление ингридиента")
    public ResponseEntity<Void> deleteIngrediends(@PathVariable long id) {
        if (ingredientsService.deleteIngrediends(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/all")
    @Operation(description = "Получение списка всех игредиентов")
    public Collection<Ingredients> getAllIngredient() {
        return ingredientsService.getAllIngredient();
    }
}
//
