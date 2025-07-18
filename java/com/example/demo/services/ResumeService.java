package com.example.demo.services;

import com.example.model.Resume;
import com.example.model.User;
import com.example.repositories.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public List<Resume> getResumesByCategory(Long categoryId) {
        return resumeRepository.findByCategoryId(categoryId);
    }

    public List<Resume> getUserResumes(Long userId) {
        return resumeRepository.findByApplicantId(userId);
    }

    public Resume updateResume(Resume resume) {
        return resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setName(resume.getName());
        resume.setCategory(resume.getCategory());
        resume.setSalary(resume.getSalary());
        resume.setActive(resume.isActive());
        return resumeRepository.save(resume);
    }

    public void insertResume(Resume resume) {
        resumeRepository.deleteById(id);
    }
}
