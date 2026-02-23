package com.forohub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
public class ControllerHola {

    @GetMapping("/hola")
    public Map<String, Object> hola() {
        return Map.of(
                "service", "forohub-api",
                "status", "UP",
                "timestamp", OffsetDateTime.now().toString()
        );
    }
}