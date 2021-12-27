package com.example.kursovoi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("https://spring-db-course.herokuapp.com")
public class MainController {

    @GetMapping("https://spring-db-course.herokuapp.com")
    public ResponseEntity mainPage(){
        try {
            return ResponseEntity.ok().body("Главная страница");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

}