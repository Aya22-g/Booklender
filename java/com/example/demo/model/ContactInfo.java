package com.example.demo.model;

import lombok.Data;

@Data
public class ContactInfo {
    private Integer id;
    private String value;
    private Resume resume;
    private ContactType type;
}
