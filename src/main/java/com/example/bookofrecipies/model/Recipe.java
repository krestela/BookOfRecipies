package com.example.bookofrecipies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Recipe {
    private String name;
    private int time;
    private List <Ingridients> ingridients = new ArrayList<>();
    private List steps = new ArrayList<>();

}