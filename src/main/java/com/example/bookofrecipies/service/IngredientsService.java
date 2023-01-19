package com.example.bookofrecipies.service;

import com.example.bookofrecipies.model.Ingridients;

public interface IngredientsService {

    void getAllIngredient();

    long addNewIngredient(Ingridients ingridients);
    Ingridients getIngredient(long id);

    Ingridients editIngredients(long id, Ingridients ingridients);

    boolean deleteIngrediends(long id);
}
