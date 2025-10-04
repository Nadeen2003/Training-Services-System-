package com.example.attendanceservice.controller;

import com.example.attendanceservice.model.Attendance;
import com.example.attendanceservice.repo.AttendanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceRepository repo;

    public AttendanceController(AttendanceRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Attendance> all() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Attendance> add(@RequestBody Attendance a) {
        return ResponseEntity.status(201).body(repo.save(a));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> one(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
