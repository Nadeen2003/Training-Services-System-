package com.example.attendanceservice.controller;

import com.example.attendanceservice.model.Attendance;
import com.example.attendanceservice.repo.AttendanceRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        List<Attendance> attendance = repo.findAll();
        return ResponseEntity.ok(attendance);
    }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@Valid @RequestBody Attendance attendance) {
        Attendance savedAttendance = repo.save(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @Valid @RequestBody Attendance attendance) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        attendance.setId(id);
        Attendance updatedAttendance = repo.save(attendance);
        return ResponseEntity.ok(updatedAttendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
