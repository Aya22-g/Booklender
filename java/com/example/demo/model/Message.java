package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Integer id;
    private String content;
    private LocalDateTime timestamp;

    private RespondedApplicant respondedApplicant;
}
