package com.example.kursovoi.controller;

import com.example.kursovoi.model.*;
import com.example.kursovoi.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    private com.example.kursovoi.service.GroupsService groupsService = new GroupsService();

    @GetMapping("/getAllGroups")
    public ResponseEntity getAllGroups() {
        try {
            return ResponseEntity.ok().body(groupsService.getAllGroups());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getGroup(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(groupsService.getGroup(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteGroup(@RequestParam Long id) {
        try {
            groupsService.deleteGroup(id);
            return ResponseEntity.ok().body("Группа удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity editGroup(@RequestParam Long id, String title, Long dep_id, Long praepostor_id, Long sum_year, String direction_on_study, Long curator_id, Long size) {
        try {
            Groups Groups = new Groups(id, title, dep_id, praepostor_id, sum_year, direction_on_study, curator_id, size);
            groupsService.editGroup(id, Groups);
            return ResponseEntity.ok().body("Группа изменёна");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addGroup(@RequestParam String title, Long dep_id, Long praepostor_id, Long sum_year, String direction_on_study, Long curator_id, Long size) {
        try {
            Groups newGroups = new Groups(title, dep_id, praepostor_id, sum_year, direction_on_study, curator_id, size);
            groupsService.addGroup(newGroups);
            return ResponseEntity.ok().body("Группа успешно создана");
        } catch (Exception we) {
            return ResponseEntity.badRequest().body(we.getMessage());
        }
    }
}
