package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.IngredientsService;
import com.example.bookofrecipies.service.RecipeService;

import java.util.Map;
import java.util.TreeMap;

public class BookOfRecipeimpl implements RecipeService, IngredientsService {
    private static Map<Long, Recipe> addRecipe = new TreeMap<>();
    private static Map<Long, Ingridients> addIngr = new TreeMap<>();
    private static long id = 0;

    @Override
    public long addNewRecipe(Recipe recipe) {
        addRecipe.put(id++, recipe);
        return 0;
    }

    @Override
    public Recipe getRecipe(long id) {
        for (Map<Long, Recipe> getRecipes : addRecipe.values()) {
            Recipe recipe1 = getRecipes.get(id);
            if (recipe1 != null) {
                return recipe1;
            }
        }
        return null;
    }
    @Override
    public void addNewIngredient(Ingridients ingridients) {
        addIngr.put(id++, ingridients);
    }
    @Override
    public Ingridients getIngredient(long id) {
        for (Map<Long, Ingridients> ingredient : addIngr.values()) {
            Ingridients ingridients = ingredient.get(id);
            if (ingridients != null) {
                return ingridients;
            }
        }
        return null;
    }

}
