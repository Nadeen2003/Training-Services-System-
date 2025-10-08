package com.example.certificationservice.repo;

import com.example.certificationservice.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByStudentId(String studentId);
    List<Certificate> findByCourseId(Long courseId);
    List<Certificate> findByStudentIdAndCourseId(String studentId, Long courseId);
}
