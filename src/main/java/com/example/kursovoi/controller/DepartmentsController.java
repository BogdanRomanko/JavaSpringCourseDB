package com.example.kursovoi.controller;

import com.example.kursovoi.model.Departments;
import com.example.kursovoi.service.DepartmentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {

    private com.example.kursovoi.service.DepartmentsService departmentsService = new DepartmentsService();

    @GetMapping("/getAllDepartments")
    public ResponseEntity getAllDepartments(){
        try {
            return ResponseEntity.ok().body(departmentsService.getAllDepartments());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getOneDepartment(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok().body(departmentsService.getDepartment(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteDepartment(@RequestParam("id") Long id) {
        try {
            departmentsService.deleteDepartment(id);
            return ResponseEntity.ok().body("Кафедра удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity editDepartment(@RequestParam("id") Long id,
                                         @RequestParam("title") String title,
                                         @RequestParam("head_id") Long head_id) {
        try {
            Departments newDepartment = new Departments(id, title, head_id);
            departmentsService.editDepartment(id, newDepartment);
            return ResponseEntity.ok().body("Кафедра изменена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addWorker(@RequestParam("title") String title,
                                    @RequestParam("head_id") Long head_id) {
        try {
            Departments department = new Departments(title, head_id);
            departmentsService.addDepartment(department);
            return ResponseEntity.ok().body("Кафедра успешно создана");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
