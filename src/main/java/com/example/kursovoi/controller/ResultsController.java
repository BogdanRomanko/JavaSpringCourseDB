package com.example.kursovoi.controller;

import com.example.kursovoi.model.*;
import com.example.kursovoi.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/results")
public class ResultsController {

    private com.example.kursovoi.service.ResultsService resultsService = new ResultsService();

    @GetMapping("/getAllResults")
    public ResponseEntity getAllResults() {
        try {
            return ResponseEntity.ok().body(resultsService.getAllResults());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getResult(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(resultsService.getResult(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteResult(@RequestParam Long id) {
        try {
            resultsService.deleteResult(id);
            return ResponseEntity.ok().body("Результат удалён");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity editResult(@RequestParam Long id, Long statement_id, Date date, Long classroom, Long group_id, Long subject_id, Long teacher_id, Long student_id, Long value) {
        try {
            Results newResult = new Results(id, statement_id, date, classroom, group_id, subject_id, teacher_id, student_id, value);
            resultsService.editResult(id, newResult);
            return ResponseEntity.ok().body("результат изменён");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addResult(@RequestParam Long statement_id, Date date, Long classroom, Long group_id, Long subject_id, Long teacher_id, Long student_id, Long value) {
        try {
            Results result = new Results(statement_id, date, classroom, group_id, subject_id, teacher_id, student_id, value);
            resultsService.addResult(result);
            return ResponseEntity.ok().body("Результат успешно добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
