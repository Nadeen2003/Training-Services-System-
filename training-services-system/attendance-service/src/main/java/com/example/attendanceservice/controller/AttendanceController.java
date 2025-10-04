package com.example.attendanceservice.controller;

import com.example.attendanceservice.model.Attendance;
import com.example.attendanceservice.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
  private final AttendanceService service;
  public AttendanceController(AttendanceService service){ this.service = service; }

  @GetMapping("/")
  public ResponseEntity<?> all(){ return ResponseEntity.ok(service.all()); }

  @PostMapping("/{id}/complete")
  public ResponseEntity<Attendance> complete(@PathVariable Long id){
    var a = service.complete(id);
    return a != null ? ResponseEntity.ok(a) : ResponseEntity.notFound().build();
  }
}
