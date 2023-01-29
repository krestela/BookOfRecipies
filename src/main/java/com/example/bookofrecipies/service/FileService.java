package com.example.bookofrecipies.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public interface FileService {
    boolean saveToFile(String json);

    String readToFile();

    boolean cleanDataFile();
}
