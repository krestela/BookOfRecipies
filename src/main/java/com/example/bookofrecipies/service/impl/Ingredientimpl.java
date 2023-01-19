package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.service.IngredientsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        for (Map<Long, Ingridients> ingredient : addIngr.values()) {
            Ingridients ingridients = ingredient.get(id);
            if (ingridients != null) {
                return ingridients;
            }
        }
        return null;
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
    public void getAllIngredient() {
        ArrayList<Map.Entry<Long, Ingridients>> allIngredients = new ArrayList<>(addIngr.entrySet());
    }
}
