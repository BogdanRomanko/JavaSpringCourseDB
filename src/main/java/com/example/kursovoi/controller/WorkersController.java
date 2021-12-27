package com.example.kursovoi.controller;

import com.example.kursovoi.model.*;
import com.example.kursovoi.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("https://spring-db-course.herokuapp.com//workers")
public class WorkersController {

    private com.example.kursovoi.service.WorkersService workersService = new WorkersService();

    @GetMapping("/getAllWorkers")
    public ResponseEntity getAllWorkers(){
        try {
            return ResponseEntity.ok().body(workersService.getAllWorkers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getOneWorker(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(workersService.getWorker(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteWorker(@RequestParam Long id) {
        try {
            workersService.deleteWorker(id);
            return ResponseEntity.ok().body("Работник удалён");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity editWorker(@RequestParam Long id, String name, Long dep_id, String role) {
        try {
            Workers newWorker = new Workers(id, name, dep_id, role);
            workersService.editWorker(id, newWorker);
            return ResponseEntity.ok().body("Работник изменён");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addWorker(@RequestParam String name, Long dep_id, String role) {
        try {
            Workers newWorker = new Workers(name, dep_id, role);
            workersService.addWorker(newWorker);
            return ResponseEntity.ok().body("Работник успешно создан");
        } catch (Exception we) {
            return ResponseEntity.badRequest().body(we.getMessage());
        }
    }

}