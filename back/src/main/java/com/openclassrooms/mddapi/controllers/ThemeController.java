package com.openclassrooms.mddapi.controllers;


import com.openclassrooms.mddapi.api.ThemesApi;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.services.ThemeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThemeController implements ThemesApi {


    private final ThemeServices themeService;

    public ThemeController(ThemeServices themeService) {
        this.themeService = themeService;
    }

    @GetMapping(
            value = "/themes",
            produces = {"application/json"}
    )
    public ResponseEntity<List<Theme>> themesGet() {
        return ResponseEntity.ok(themeService.getThemeList());
    }
}
