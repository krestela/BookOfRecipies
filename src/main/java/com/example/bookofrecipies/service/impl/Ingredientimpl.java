package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.model.Ingridients;
import com.example.bookofrecipies.model.Recipe;
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
    final private FileService fileService;
    private static TreeMap<Long, Ingridients> addIngr = new TreeMap<>();
    private static long id = 0;

    public Ingredientimpl(FileService fileService) {
        this.fileService = fileService;
    }


    @Override
    public long addNewIngredient(Ingridients ingridients) {
        addIngr.put(id++, ingridients);
        saveToFile();
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
            addIngr = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Ingridients>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
