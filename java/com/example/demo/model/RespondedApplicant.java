package com.example.demo.model;

import lombok.Data;

import java.util.Set;

@Data
public class RespondedApplicant {
    private String id;
    private boolean confirmation;

    private Resume resume;
    private Vacancy vacancy;

    private Set<Message> messages;


