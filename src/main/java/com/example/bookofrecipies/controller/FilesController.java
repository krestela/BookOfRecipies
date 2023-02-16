package com.example.bookofrecipies.controller;

import com.example.bookofrecipies.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/exportRecipe")
    public ResponseEntity<InputStreamResource> dowloadRecipeFile() throws FileNotFoundException {
        File file = fileService.getDataFileRecipe();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);

        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/exportIngredients")
    public ResponseEntity<InputStreamResource> dowloadIngredientFile() throws FileNotFoundException {
        File file1 = fileService.getDataFileIngredient();
        if (file1.exists()) {
            InputStreamResource resource1 = new InputStreamResource(new FileInputStream(file1));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientLog.json\"")
                    .contentLength(file1.length())
                    .body(resource1);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping(value = "/importRecipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {
        File dataFile = fileService.getDataFileRecipe();

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/importIngredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        fileService.cleanDataFile();
        File dataFile = fileService.getDataFileIngredient();

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
