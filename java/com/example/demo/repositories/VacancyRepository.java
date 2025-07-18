package com.example.demo.repositories;

import com.example.model.Vacancy;

import java.util.List;

public interface VacancyRepository { extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByCategoryId(Long categoryId);
    List<Vacancy> findByIsActiveTrue();
    List<Vacancy> findByAuthorId(Long authorId);
}
