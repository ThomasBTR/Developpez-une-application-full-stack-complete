package com.openclassrooms.mddapi.controllers;


import com.openclassrooms.mddapi.api.ThemesApi;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.services.ThemeServices;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(
            value = "/themes/{theme_id}/subscribe/{user_id}",
            produces = { "application/json" }
    )
    public ResponseEntity<Void> themesThemeIdSubscribeUserIdPost(
            @Parameter(name = "theme_id", description = "ID of the theme to subscribe to", required = true, in = ParameterIn.PATH) @PathVariable("theme_id") Long themeId,
            @Parameter(name = "user_id", description = "ID of the user subscribing", required = true, in = ParameterIn.PATH) @PathVariable("user_id") Long userId
    ) {
        themeService.subscribeToTheme(themeId, userId);
        return ResponseEntity.ok().build();
    }
}
