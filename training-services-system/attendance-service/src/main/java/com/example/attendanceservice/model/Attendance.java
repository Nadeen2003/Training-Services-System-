package com.example.attendanceservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendance")
@Data @NoArgsConstructor @AllArgsConstructor
public class Attendance {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long registrationId;
  private Long courseId;
  private String studentName;
  private String status; // e.g., STARTED, COMPLETED
}
