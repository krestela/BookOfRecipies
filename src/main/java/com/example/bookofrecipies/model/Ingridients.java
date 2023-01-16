package com.example.bookofrecipies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingridients {
private String name;
private int quantity;
private int measure;
}
