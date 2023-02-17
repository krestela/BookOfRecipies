package com.example.bookofrecipies.service;

import com.example.bookofrecipies.model.Recipe;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;
@Service
public interface RecipeService {

    long addNewRecipe(Recipe recipe);

    Recipe getRecipe(long id);

    Recipe editRecipe(long id, Recipe recipe);

    boolean deleteRecipe(long id);

    Collection<Recipe> getAllRecipe();

    Path createRecipeText() throws IOException;
}
