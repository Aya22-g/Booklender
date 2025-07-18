package com.example.demo.repositories;

import com.example.model.Resume;

import java.util.List;

public interface ResumeRepository  extends JpaRepository<Resume, Long> {
    List<Resume> findByCategoryId(Long categoryId);
    List<Resume> findByApplicantId(Long applicantId);
}
