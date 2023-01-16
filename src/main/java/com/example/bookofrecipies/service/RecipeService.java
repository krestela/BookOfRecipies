package com.example.bookofrecipies.service;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.model.Recipe;

public interface RecipeService {

    void addNewRecipe(Recipe recipe);

    void getRecipe(int numberId);
    void addNewIngredient(Ingridients ingridients);
    void getIngredient(int numberId);
}
