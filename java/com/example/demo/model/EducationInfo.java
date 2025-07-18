package com.example.demo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationInfo {
    private Integer id;
    private String institution;
    private String program;
    private String degree;

    private LocalDate startDate;
    private LocalDate endDate;
    private Resume resume;
}
