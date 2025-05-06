package com.example.personalblogbackend.service;

import com.example.personalblogbackend.model.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeService {
    Theme createTheme(Theme theme);
    Optional<Theme> getThemeById(Long id);
    List<Theme> getAllThemes();
    Theme updateTheme(Long id, Theme theme);
    void deleteTheme(Long id);
}
