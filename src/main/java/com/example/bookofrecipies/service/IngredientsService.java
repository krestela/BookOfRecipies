package com.example.bookofrecipies.service;

import com.example.bookofrecipies.model.Ingridients;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
public interface IngredientsService {

    Collection<Ingridients> getAllIngredient();

    long addNewIngredient(Ingridients ingridients);
    Ingridients getIngredient(long id);

    Ingridients editIngredients(long id, Ingridients ingridients);

    boolean deleteIngrediends(long id);
}
