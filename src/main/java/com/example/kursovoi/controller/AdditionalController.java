package com.example.kursovoi.controller;

import com.example.kursovoi.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/additional")
public class AdditionalController {

    private com.example.kursovoi.service.AdditionalService additionalService = new AdditionalService();

    @GetMapping("/praestors")
    public ResponseEntity getAllPraestors() {
        try {
            return ResponseEntity.ok().body(additionalService.getPraestors());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/curators")
    public ResponseEntity getAllCuratos() {
        try {
            return ResponseEntity.ok().body(additionalService.getCurators());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/budgets")
    public ResponseEntity getAllBudgets() {
        try {
            return ResponseEntity.ok().body(additionalService.getBudgets());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/contracts")
    public ResponseEntity getAllContracts() {
        try {
            return ResponseEntity.ok().body(additionalService.getContracts());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
