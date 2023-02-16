package com.example.bookofrecipies.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;

@Service
public interface FileService {
    boolean saveToFile(String json);

    String readToFile();

    boolean cleanDataFile();

    File getDataFileRecipe();

    File getDataFileIngredient();

}
