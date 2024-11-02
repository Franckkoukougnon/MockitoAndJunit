package com.tuto.mockito.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Test", description = "API pour les tests")
public class TestController {
    @GetMapping("/test")
    @Operation(summary = "Test de l'application")
    public String test() {
        return "Application is running!";
    }
}
