package com.example.courseservice.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/")
    public Map<String, String> root() {
        return Map.of("service", "course-service", "status", "ok");
    }

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("service", "course-service", "ping", "pong");
    }
}
