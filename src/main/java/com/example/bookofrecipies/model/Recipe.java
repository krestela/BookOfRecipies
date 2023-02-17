package com.example.bookofrecipies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private int time;
    private List <Ingredients> ingridients = new ArrayList<>();
    private List steps = new ArrayList<>();
}