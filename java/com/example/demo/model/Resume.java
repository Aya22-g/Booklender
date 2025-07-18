package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class Resume {
    private Integer id;
    private String name;
    private double salary;

    private boolean isActive;
    private User applicant;
    private Category category;

    private Set<EducationInfo> educations;
    private Set<WorkExperienceInfo> workExperiences;
    private Set<ContactInfo> contactInfos;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;

