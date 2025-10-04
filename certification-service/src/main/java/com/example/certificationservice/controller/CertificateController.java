package com.example.certificationservice.controller;

import com.example.certificationservice.model.Certificate;
import com.example.certificationservice.repo.CertificateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateRepository repo;

    public CertificateController(CertificateRepository repo) {
        this.repo = repo;
    }

    // GET /certificates  -> رجّع الكل
    @GetMapping
    public List<Certificate> all() {
        return repo.findAll();
    }

    // POST /certificates  (body: {"registrationId":1, "status":"ISSUED"})
    @PostMapping
    public ResponseEntity<Certificate> create(@RequestBody Map<String, Object> body) {
        Long registrationId = ((Number) body.get("registrationId")).longValue();
        String status = body.getOrDefault("status", "NOT_ISSUED").toString();
        boolean issued = "ISSUED".equalsIgnoreCase(status) || "true".equalsIgnoreCase(status);

        Certificate c = new Certificate(registrationId, issued);
        return ResponseEntity.status(201).body(repo.save(c));
    }

    // GET /certificates/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Certificate> one(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
