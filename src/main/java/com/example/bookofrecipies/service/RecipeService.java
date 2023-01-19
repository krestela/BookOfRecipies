package com.example.bookofrecipies.service;

import com.example.bookofrecipies.model.Recipe;

public interface RecipeService {

    long addNewRecipe(Recipe recipe);

    Recipe getRecipe(long id);

    Recipe editRecipe(long id, Recipe recipe);

    boolean deleteRecipe(long id);

    void getAllRecipe();
}
