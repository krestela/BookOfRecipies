package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.RecipeService;

import java.util.Map;
import java.util.TreeMap;

public class Recipeimpl implements RecipeService {
    private static Map<Long, Recipe> addRecipe = new TreeMap<>();
    private static Map<Long, Ingridients> addIngr = new TreeMap<>();
    private static long id = 0;

    @Override
    public void addNewRecipe(Recipe recipe) {
        addRecipe.put(id++, recipe);
    }

    @Override
    public void getRecipe(int numberId) {
        if (addRecipe.containsKey(numberId)) {
            addRecipe.values();
        } else {
            System.out.println("Рецепт не найден");
        }

    }

    @Override
    public void addNewIngredient(Ingridients ingridients) {
        addIngr.put(id++, ingridients);
    }

    @Override
    public void getIngredient(int numberId) {
        if (addIngr.containsKey(numberId)) {
            addIngr.values();
        } else {
            System.out.println("Ингредиент не найден");
        }
    }
}
