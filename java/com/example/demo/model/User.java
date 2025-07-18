package com.example.demo.model;

import lombok.Data;

import java.util.Set;

@Data
public class User {

    private Integer id;
    private String name;
    private int age;
    private String email;
    private String password;

    private String phoneNumber;
    private String avatar;
    private String accountType;

    private Set<Vacancy> vacancies;
    private Set<Resume> resumes;
}
