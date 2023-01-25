package com.example.bookofrecipies.service;

import com.example.bookofrecipies.model.Ingridients;

import java.util.Collection;

public interface IngredientsService {

    Collection<Ingridients> getAllIngredient();

    long addNewIngredient(Ingridients ingridients);
    Ingridients getIngredient(long id);

    Ingridients editIngredients(long id, Ingridients ingridients);

    boolean deleteIngrediends(long id);
}
