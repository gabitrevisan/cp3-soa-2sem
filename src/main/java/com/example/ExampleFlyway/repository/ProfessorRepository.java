package com.example.ExampleFlyway.repository;

import com.example.ExampleFlyway.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}