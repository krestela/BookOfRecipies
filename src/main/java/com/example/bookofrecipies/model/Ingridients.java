package com.example.bookofrecipies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingridients {
private String name;
private int quantity;
private int measure;
}
