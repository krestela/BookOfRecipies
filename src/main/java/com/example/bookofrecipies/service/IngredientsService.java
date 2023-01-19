package com.example.bookofrecipies.service;

import com.example.bookofrecipies.model.Ingridients;

public interface IngredientsService {
    void addNewIngredient(Ingridients ingridients);
    Ingridients getIngredient(long id);
}
