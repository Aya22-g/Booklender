package com.example.demo.services;

import com.example.demo.model.RespondedApplicant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ApplicantService {
    private final ApplicantService respondedApplicantService;


    public ResponseEntity<Applicant> respondToVacancy( {
        return ResponseEntity.ok(ApplicantService.createResponse(response));
    }

    @GetMapping("/vacancy/{vacancyId}")
    public ResponseEntity<List<RespondedApplicant>> getResponsesForVacancy(
            @PathVariable Long vacancyId) {
        return ResponseEntity.ok(
                ApplicantService.getResponsesForVacancy(vacancyId));
    }
}
