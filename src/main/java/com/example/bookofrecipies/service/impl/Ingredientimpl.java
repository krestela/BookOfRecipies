package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.exception.IngredientException;
import com.example.bookofrecipies.model.Ingredients;
import com.example.bookofrecipies.service.FileService;
import com.example.bookofrecipies.service.IngredientsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.*;
@Service
public class Ingredientimpl implements IngredientsService {
    private final FileService fileService;
    private static TreeMap<Long, Ingredients> addIngr = new TreeMap<>();
    private static long id = 0;

    public Ingredientimpl(FileService fileService) {
        this.fileService = fileService;
    }



    @Override
    public long addNewIngredient(Ingredients ingredients) {
        addIngr.put(id++, ingredients);
        saveToFile();
        return id++;
    }

    @Override
    public Ingredients getIngredient(long id) {
            if (!addIngr.containsKey(id)) {
                throw new NotFoundException("Ингредиент не найден");

            }
            return addIngr.get(id);
        }


    @Override
    public Ingredients editIngredients(long id, Ingredients ingredients) throws IngredientException {
        if (addIngr.containsValue(ingredients)) {
            throw new IngredientException();
        }
        addIngr.containsKey(id);
        addIngr.put(id, ingredients);
        return ingredients;
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
    public Collection<Ingredients> getAllIngredient() {
        return addIngr.values();
    }

    private void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(addIngr);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile(){

        try {
            String json = fileService.readToFile();
            addIngr = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Ingredients>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
