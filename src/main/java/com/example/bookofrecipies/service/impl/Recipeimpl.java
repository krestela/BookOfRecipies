package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.model.Ingredients;
import com.example.bookofrecipies.model.Recipe;
import com.example.bookofrecipies.service.FileService;
import com.example.bookofrecipies.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

        if (addRecipe.containsValue(recipe)) {
            throw new NotFoundException("Id не найден");

        }
        addRecipe.put(id, recipe);
        saveToFile();
        return recipe;
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

    @Override
    public Path createRecipeText() throws IOException {
        addRecipe.getOrDefault(id, null);
        Path path = fileService.createTempFile("allRecipe");
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
        for (Recipe recipe : addRecipe.values()) {
            StringBuilder ingr = new StringBuilder();
            for (Ingredients ingredients : recipe.getIngridients()){
                ingr.append(ingredients).append(",\n");
;            }
                writer.append(recipe.getName() + ": " + recipe.getTime() + ": " + recipe.getIngridients() + ": " + recipe.getSteps());

            }
        }
        return path;
    }

}
