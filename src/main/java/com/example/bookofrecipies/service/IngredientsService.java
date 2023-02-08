package com.example.bookofrecipies.service;

import com.example.bookofrecipies.exception.IngredientException;
import com.example.bookofrecipies.model.Ingredients;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Component
@Service
public interface IngredientsService {

    Collection<Ingredients> getAllIngredient();

    long addNewIngredient(Ingredients ingredients);
    Ingredients getIngredient(long id);

    Ingredients editIngredients(long id, Ingredients ingredients) throws IngredientException;

    boolean deleteIngrediends(long id);
}
