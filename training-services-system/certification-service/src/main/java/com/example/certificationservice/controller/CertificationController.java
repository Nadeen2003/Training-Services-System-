package com.example.certificationservice.controller;

import com.example.certificationservice.service.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificationController {
  private final CertificationService service;
  public CertificationController(CertificationService service){ this.service = service; }

  @GetMapping("/")
  public ResponseEntity<?> all(){ return ResponseEntity.ok(service.all()); }
}
