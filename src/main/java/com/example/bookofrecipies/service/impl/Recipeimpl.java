package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.FileService;
import com.example.bookofrecipies.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class Recipeimpl implements RecipeService {
    final private FileService fileService;
    private static TreeMap<Long, Recipe> addRecipe = new TreeMap<>();
    private static long id = 0;

    public Recipeimpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public long addNewRecipe(Recipe recipe) {
        addRecipe.put(id++, recipe);
        saveToFile();
        return id++;
    }

    @Override
    public Recipe getRecipe(long id) {
        if (!addRecipe.containsKey(id)) {
            throw new NotFoundException("Рецепт не найден");

        }
        return addRecipe.get(id);
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe) {

        if (addRecipe.containsKey(id)) {
            addRecipe.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(long id) {
        if (addRecipe.containsKey(id)) {
            addRecipe.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Collection<Recipe> getAllRecipe() {
        return addRecipe.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(addRecipe);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    private void readFromFile() {

        try {
            String json = fileService.readToFile();
            addRecipe = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
