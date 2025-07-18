package com.example.demo.model;

import lombok.Data;

import java.util.Set;

@Data
public class Category {
    private Integer id;
    private String name;

    private Category parent;

    private Set<Category> parents;
}
