package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.service.IngredientsService;
import org.webjars.NotFoundException;

import java.util.*;

public class Ingredientimpl implements IngredientsService {
    private static Map<Long, Ingridients> addIngr = new TreeMap<>();
    private static long id = 0;



    @Override
    public long addNewIngredient(Ingridients ingridients) {
        addIngr.put(id++, ingridients);
        return id++;
    }

    @Override
    public Ingridients getIngredient(long id) {
            if (!addIngr.containsKey(id)) {
                throw new NotFoundException("Ингредиент не найден");

            }
            return addIngr.get(id);
        }


    @Override
    public Ingridients editIngredients(long id, Ingridients ingridients) {
        if (addIngr.containsKey(id)) {
            addIngr.put(id, ingridients);
            return ingridients;
        }
        return null;
    }

    @Override
    public boolean deleteIngrediends(long id) {
        if (addIngr.containsKey(id)) {
            addIngr.remove(id);
            return true;
        }
        return false;
    }
    @Override
    public Collection<Ingridients> getAllIngredient() {
        return addIngr.values();
    }
}
