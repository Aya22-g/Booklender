package com.example.demo.services;

import com.example.repositories.RespondedApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespondedApplicantService {
    private final RespondedApplicantRepository respondedApplicantRepository;

    public List<RespondedApplicant> getResponsesForVacancy(Long vacancyId) {
      return respondedApplicantRepository.findByVacancyId(vacancyId);
    }

    public List<RespondedApplicant> getUserResponses(Long userId) {
        return respondedApplicantRepository.findByResumeApplicantId(userId);
    }

    public RespondedApplicant createResponse(RespondedApplicant response) {
        return respondedApplicantRepository.save(response);
    }

    public void confirmResponse(Long responseId) {
        RespondedApplicant response = respondedApplicantRepository.findById( responseId)
                .orElseThrow(() -> new RuntimeException("Response not found"));
        response.setConfirmation(true);
        respondedApplicantRepository.save(response);
    }
}
