package com.example.certificationservice.service;

import com.example.certificationservice.model.Certificate;
import com.example.certificationservice.repository.CertificateRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CertificationService {
  private final CertificateRepository repo;
  public CertificationService(CertificateRepository repo){ this.repo = repo; }

  public List<Certificate> all(){ return repo.findAll(); }

  @RabbitListener(queues = "${app.queues.courseCompleted}")
  public void consumeCourseCompleted(Map<String, Object> payload){
    String student = (String) payload.get("studentName");
    Long courseId = ((Number)payload.get("courseId")).longValue();
    Certificate c = new Certificate(null, student, courseId, "ISSUED");
    repo.save(c);
  }
}
