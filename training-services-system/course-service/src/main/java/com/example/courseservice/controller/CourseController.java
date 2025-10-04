package com.example.courseservice.controller;

import com.example.courseservice.model.Course;
import com.example.courseservice.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
  private final CourseService service;
  public CourseController(CourseService service){ this.service = service; }

  @GetMapping("/")
  public ResponseEntity<List<Course>> getAll(){ return ResponseEntity.ok(service.getAll()); }

  @GetMapping("/{id}")
  public ResponseEntity<Course> getOne(@PathVariable Long id){
    Course c = service.getById(id);
    return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
  }

  @PostMapping("/add")
  public ResponseEntity<Course> add(@RequestBody Course c){
    return ResponseEntity.status(201).body(service.add(c));
  }
}
