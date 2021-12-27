package com.example.kursovoi.controller;

import com.example.kursovoi.model.*;
import com.example.kursovoi.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attestation")
public class AttestationController {

    private com.example.kursovoi.service.AttestationService attestationService = new AttestationService();

    @GetMapping("/getAllAttestations")
    public ResponseEntity getAllAttestations() {
        try {
            return ResponseEntity.ok().body(attestationService.getAllAttestations());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getAttestation(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(attestationService.getAttestation(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAttestation(@RequestParam Long id) {
        try {
            attestationService.deleteAttestation(id);
            return ResponseEntity.ok().body("Аттестация удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity editAttestation(@RequestParam Long id, Long subject_id, Long group_id, Long student_id, Long value) {
        try {
            Attestation newAttestation = new Attestation(id, subject_id, group_id, student_id, value);
            attestationService.editAttestation(id, newAttestation);
            return ResponseEntity.ok().body("Аттестация изменена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addAttestation(@RequestParam Long subject_id, Long group_id, Long student_id, Long value) {
        try {
            Attestation attestation = new Attestation(subject_id, group_id, student_id, value);
            attestationService.addAttestation(attestation);
            return ResponseEntity.ok().body("Аттестация успешно добавлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
