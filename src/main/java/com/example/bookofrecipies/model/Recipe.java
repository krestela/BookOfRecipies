package com.example.bookofrecipies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    private String name;
    private int time;
    private Ingridients ingridients;
    private List steps = new ArrayList<>();

}
