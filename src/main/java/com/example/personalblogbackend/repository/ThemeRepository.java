package com.example.personalblogbackend.repository;

import com.example.personalblogbackend.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    boolean existsByName(String name);
}
