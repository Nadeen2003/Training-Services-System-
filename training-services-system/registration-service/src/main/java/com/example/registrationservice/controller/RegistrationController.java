package com.example.registrationservice.controller;

import com.example.registrationservice.model.Registration;
import com.example.registrationservice.service.RegistrationService;
import com.example.registrationservice.vo.RegistrationWithCourse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
  private final RegistrationService service;
  public RegistrationController(RegistrationService service){ this.service = service; }

  @GetMapping("/")
  public ResponseEntity<List<Registration>> getAll(){ return ResponseEntity.ok(service.getAll()); }

  @GetMapping("/{id}")
  public ResponseEntity<Registration> getOne(@PathVariable Long id){
    Registration r = service.getById(id);
    return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
  }

  @PostMapping("/add")
  public ResponseEntity<Registration> add(@RequestBody Registration r){
    return ResponseEntity.status(201).body(service.add(r));
  }

  @GetMapping("/{id}/with-course")
  public ResponseEntity<RegistrationWithCourse> getWithCourse(@PathVariable Long id){
    RegistrationWithCourse rc = service.getWithCourse(id);
    return rc != null ? ResponseEntity.ok(rc) : ResponseEntity.notFound().build();
  }
}
