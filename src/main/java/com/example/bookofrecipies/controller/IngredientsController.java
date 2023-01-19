package com.example.bookofrecipies.controller;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.service.IngredientsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class IngredientsController {
    private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }
    @PostMapping("/addingr")
    public void addIngredient(@RequestParam Ingridients ingridients) {
        ingredientsService.addNewIngredient(ingridients);
    }

    @GetMapping("/getingr")
    public void getIngredient(@RequestParam int numberId) {
        ingredientsService.getIngredient(numberId);
    }
}
