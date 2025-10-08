package com.example.certificationservice.controller;

import com.example.certificationservice.model.Certificate;
import com.example.certificationservice.repo.CertificateRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateRepository repo;

    public CertificateController(CertificateRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<Certificate>> getAllCertificates(
            @RequestParam(required = false) String studentId,
            @RequestParam(required = false) Long courseId) {
        
        List<Certificate> certificates;
        if (studentId != null && courseId != null) {
            certificates = repo.findByStudentIdAndCourseId(studentId, courseId);
        } else if (studentId != null) {
            certificates = repo.findByStudentId(studentId);
        } else if (courseId != null) {
            certificates = repo.findByCourseId(courseId);
        } else {
            certificates = repo.findAll();
        }
        
        return ResponseEntity.ok(certificates);
    }

    @PostMapping
    public ResponseEntity<Certificate> createCertificate(@Valid @RequestBody Certificate certificate) {
        Certificate savedCertificate = repo.save(certificate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCertificate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificate(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
