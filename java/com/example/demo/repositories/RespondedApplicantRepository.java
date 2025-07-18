package com.example.demo.repositories;


import com.example.model.RespondedApplicant;

import java.util.List;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {
    List<RespondedApplicant> findByVacancyId(Long vacancyId);
    List<RespondedApplicant> findByResumeApplicantId(Long applicantId);
}
