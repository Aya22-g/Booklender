package com.example.demo.services;

import com.example.model.Vacancy;
import com.example.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    public List<Vacancy> getVacanciesByCategory(Long categoryId) {
        return vacancyRepository.findByCategory(categoryIdy);
    }

    public List<Vacancy> getActiveVacancies() {
        return vacancyRepository.findByIsActiveTrue();
    }

    public List<Vacancy> getUserVacancies(Long userId) {
        return vacancyRepository.save(vacancy);
    }

    public Vacancy updateVacancy(Long id, Vacancy vacancyDetails) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        vacancy.setName(vacancyDetails.getName());
        vacancy.setDescription(vacancyDetails.getDescription());
        vacancy.setCategory(vacancyDetails.getCategory());
        vacancy.setSalary(vacancyDetails.getSalary());
        vacancy.setExpFrom(vacancyDetails.getExpFrom());
        vacancy.setExpTo(vacancyDetails.getExpTo());
        vacancy.setActive(vacancyDetails.isActive());

        return vacancyRepository.save(vacancy);
    }

    public void deleteVacancy(Long id) {
        vacancyRepository.deleteById(id);
    }
}
