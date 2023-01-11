package com.example.bookofrecipies;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String appGet() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String page() {
        return "Имя ученика: Адигамова Кристина\n" +
                "\nНазвание проекта: Книга рецептов\n "+
                "\nДата создание: 11.01.2023\n"+
                "\nОписание: \n" +
                "\nНазвание проекта: Книга рецептов\n" +
                "\nФункции проекта: Хранить и выводить информацию разных рецептов со всего мира\n" +
                "\nТехнологии, которые используются: Maven & Gradle\n" +
                "\nЯзык проекта: Java";
    }
}
