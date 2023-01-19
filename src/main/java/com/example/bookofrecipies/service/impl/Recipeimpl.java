package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.RecipeService;

import java.util.*;

public class Recipeimpl implements RecipeService {
    private static Map<Long, Recipe> addRecipe = new TreeMap<>();
    private static Map<Long, Ingridients> addIngr = new TreeMap<>();
    private static long id = 0;

    @Override
    public long addNewRecipe(Recipe recipe) {
        addRecipe.put(id++, recipe);
        return id++;
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
    public Recipe editRecipe(long id, Recipe recipe) {

        if (addRecipe.containsKey(id)) {
            addRecipe.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(long id) {
        if (addRecipe.containsKey(id)) {
            addRecipe.remove(id);
            return true;
        }
        return false;
    }

}
