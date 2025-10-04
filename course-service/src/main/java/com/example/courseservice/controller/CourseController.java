package com.example.courseservice.controller;

import com.example.courseservice.model.Course;
import com.example.courseservice.repo.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository repo;

    public CourseController(CourseRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Course> all() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Course> add(@RequestBody Course c) {
        return ResponseEntity.status(201).body(repo.save(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> one(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
