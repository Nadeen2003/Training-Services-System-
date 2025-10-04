package com.example.registrationservice.controller;

import com.example.registrationservice.model.Registration;
import com.example.registrationservice.repo.RegistrationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationRepository repo;

    public RegistrationController(RegistrationRepository repo) {
        this.repo = repo;
    }

    // GET /registrations  -> رجّع الكل
    @GetMapping
    public List<Registration> all() {
        return repo.findAll();
    }

    // POST /registrations
    @PostMapping
    public ResponseEntity<Registration> create(@RequestBody Registration r) {
        Registration saved = repo.save(r);
        return ResponseEntity.status(201).body(saved);
    }

    // GET /registrations/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Registration> one(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
