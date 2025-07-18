package com.example.demo.controller;

import com.example.model.Vacancy;
import com.example.services.VacancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class VacancyController {
    private final VacancyService vacancyService;

    @PostMapping
    public ResponseEntity<Vacancy> createVacancy(@RequestBody Vacancy vacancy) {
        return ResponseEntity.ok(vacancyService.createVacancy(vacancy));
    }

    @PutMapping("{id}")
    public ResponseEntity<Vacancy> updateVacancy(
            @PathVariable Long id,
            @RequestBody Vacancy vacancy) {
        return ResponseEntity.ok(vacancyService.updateVacancy(id,vacancy));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteVacancy(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<Vacancy>> getAllActiveVacancies() {
        return ResponseEntity.ok(vacancyService.getAllActiveVacancies());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Vacancy>> getVacanciesByCategory(
            @PathVariable Long categoryId) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCategory(categoryId));
    }


}
