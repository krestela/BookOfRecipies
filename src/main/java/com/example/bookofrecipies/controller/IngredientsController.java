package com.example.bookofrecipies.controller;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.IngredientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингридиенты", description = "Ингридиенты и методы с ними")
public class IngredientsController {
    private IngredientsService ingredientsService;

    public IngredientsController(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }
    @PostMapping("/addingr")
    @Operation(description = "Добавление нового ингридиента")
    public ResponseEntity<Long> addIngredient(@RequestBody Ingridients ingridients) {
        long id = ingredientsService.addNewIngredient(ingridients);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получения ингридиента по id")
    public ResponseEntity<Ingridients> getIngredient(@PathVariable long id) {
        Ingridients ingridients= ingredientsService.getIngredient(id);
        if (ingridients == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridients);
    }

    @PutMapping("/{id}")
    @Operation(description = "Редактирование списка ингредиентов")
    public ResponseEntity<Ingridients> editIngredients(@PathVariable long id, @RequestBody Ingridients ingridients) {
        ingredientsService.getIngredient(id);
        if (ingridients == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridients);
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
    public Collection<Ingridients> getAllIngredient() {
        return ingredientsService.getAllIngredient();
    }
}
//
