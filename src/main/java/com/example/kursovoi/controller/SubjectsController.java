package com.example.kursovoi.controller;

import com.example.kursovoi.model.*;
import com.example.kursovoi.service.SubjectsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("https://spring-db-course.herokuapp.com/subjects")
public class SubjectsController {

    private com.example.kursovoi.service.SubjectsService subjectsService = new SubjectsService();

    @GetMapping("/getAllSubjects")
    public ResponseEntity getAllSubjects() {
        try {
            return ResponseEntity.ok().body(subjectsService.getAllSubjects());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getSubject(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(subjectsService.getSubject(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteSubject(@RequestParam Long id) {
        try {
            subjectsService.deleteSubject(id);
            return ResponseEntity.ok().body("Дисциплина удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity editSubject(@RequestParam Long id, String title, Long teacher_id, Long is_exam) {
        try {
            Subjects subject = new Subjects(id, title, teacher_id, is_exam);
            subjectsService.editSubject(id, subject);
            return ResponseEntity.ok().body("Дисциплина изменёна");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addSubject(@RequestParam String title, Long teacher_id, Long is_exam) {
        try {
            Subjects newSubject = new Subjects(title, teacher_id, is_exam);
            subjectsService.addSubject(newSubject);
            return ResponseEntity.ok().body("Дисциплина успешно создана");
        } catch (Exception we) {
            return ResponseEntity.badRequest().body(we.getMessage());
        }
    }

}
