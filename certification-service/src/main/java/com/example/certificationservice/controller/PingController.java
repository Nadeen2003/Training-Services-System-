package com.example.certificationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PingController {

    @GetMapping("/")
    public Map<String, String> root() {
        return Map.of("service", "certification-service", "status", "ok");
    }

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("service", "certification-service", "ping", "pong");
    }
}
