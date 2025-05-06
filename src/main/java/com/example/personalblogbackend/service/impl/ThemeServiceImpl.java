package com.example.personalblogbackend.service.impl;

import com.example.personalblogbackend.model.Theme;
import com.example.personalblogbackend.repository.ThemeRepository;
import com.example.personalblogbackend.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Optional<Theme> getThemeById(Long id) {
        return themeRepository.findById(id);
    }

    @Override
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public Theme updateTheme(Long id, Theme theme) {
        return themeRepository.findById(id).map(existingTheme -> {
            existingTheme.setName(theme.getName());
            existingTheme.setDescription(theme.getDescription());
            return themeRepository.save(existingTheme);
        }).orElseThrow(() -> new RuntimeException("Theme not found with id " + id));
    }

    @Override
    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }
}
