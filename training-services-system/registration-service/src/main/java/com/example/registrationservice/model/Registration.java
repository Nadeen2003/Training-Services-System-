package com.example.registrationservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "registrations")
@Data @NoArgsConstructor @AllArgsConstructor
public class Registration {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String studentName;
  private Long courseId;
  private String status; // e.g., CREATED, CONFIRMED
}
