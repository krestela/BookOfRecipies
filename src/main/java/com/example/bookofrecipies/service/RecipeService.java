package com.example.bookofrecipies.service;

import com.example.bookofrecipies.model.Recipe;

import java.util.Collection;

public interface RecipeService {

    long addNewRecipe(Recipe recipe);

    Recipe getRecipe(long id);

    Recipe editRecipe(long id, Recipe recipe);

    boolean deleteRecipe(long id);

    Collection<Recipe> getAllRecipe();
}
