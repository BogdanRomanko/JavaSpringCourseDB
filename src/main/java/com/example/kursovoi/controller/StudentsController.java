package com.example.kursovoi.controller;

import com.example.kursovoi.model.Students;
import com.example.kursovoi.service.StudentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("https://spring-db-course.herokuapp.com//students")
public class StudentsController {

    private com.example.kursovoi.service.StudentsService studentsService = new StudentsService();

    @GetMapping("/getAllStudents")
    public ResponseEntity getAllStudents() {
        try {
            return ResponseEntity.ok().body(studentsService.getAllStudents());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(studentsService.getStudent(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam Long id) {
        try {
            studentsService.deleteStudent(id);
            return ResponseEntity.ok().body("Студент удалён");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity editStudent(@RequestParam Long id, String name, Long group_id, Long dep_id, String type_of_tr, Long is_budget, Long is_praestor) {
        try {
            Students student = new Students(id, name, group_id, dep_id, type_of_tr, is_budget, is_praestor);
            studentsService.editStudent(id, student);
            return ResponseEntity.ok().body("Студент изменён");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestParam String name, Long group_id, Long dep_id, String type_of_tr, Long is_budget, Long is_praestor) {
        try {
            Students newStudent = new Students(name, group_id, dep_id, type_of_tr, is_budget, is_praestor);
            studentsService.addStudent(newStudent);
            return ResponseEntity.ok().body("Студент успешно создан");
        } catch (Exception we) {
            return ResponseEntity.badRequest().body(we.getMessage());
        }
    }
}
