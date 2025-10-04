package com.example.courseservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses")
@Data @NoArgsConstructor @AllArgsConstructor
public class Course {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String instructor;
  private Integer capacity;
  private Integer availableSeats;
}
