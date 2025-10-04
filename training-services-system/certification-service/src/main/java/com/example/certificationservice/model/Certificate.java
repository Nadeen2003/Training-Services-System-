package com.example.certificationservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "certificates")
@Data @NoArgsConstructor @AllArgsConstructor
public class Certificate {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String studentName;
  private Long courseId;
  private String status; // ISSUED
}
