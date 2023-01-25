package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.RecipeService;
import org.webjars.NotFoundException;

import java.util.*;

public class Recipeimpl implements RecipeService {
    private static Map<Long, Recipe> addRecipe = new TreeMap<>();
    private static long id = 0;

    @Override
    public long addNewRecipe(Recipe recipe) {
        addRecipe.put(id++, recipe);
        return id++;
    }

    @Override
    public Recipe getRecipe(long id) {
        if (!addRecipe.containsKey(id)) {
            throw new NotFoundException("Рецепт не найден");

        }
        return addRecipe.get(id);
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
    @Override
    public Collection<Recipe> getAllRecipe() {
        return addRecipe.values();
    }

}
