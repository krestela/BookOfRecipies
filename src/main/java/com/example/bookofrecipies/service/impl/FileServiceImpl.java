package com.example.bookofrecipies.service.impl;

import com.example.bookofrecipies.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    @Value("${path.to.data.file}")
    private String dataRecipeFile;
    @Value("${name.of.data.file}")
    private String dataRecipeName;
    @Value("${path.to.ingr.file}")
    private String dataIngrFile;
    @Value("${name.of.ingr.file}")
    private String dataIngrName;

    @Override
    public boolean saveToFile(String json) {
        try {
            Files.writeString(Path.of(dataRecipeFile, dataRecipeName), json);
            Files.writeString(Path.of(dataIngrFile, dataIngrName), json);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
    @Override
    public String readToFile() {
        try {
            return Files.readString(Path.of(dataRecipeFile, dataRecipeName)) +
            Files.readString(Path.of(dataIngrFile, dataIngrName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataRecipeFile, dataRecipeName);
            Path path1 = Path.of(dataIngrFile, dataIngrName);
            Files.deleteIfExists(path);
            Files.deleteIfExists(path1);
            Files.createFile(path);
            Files.createFile(path1);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
