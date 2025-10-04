package com.example.registrationservice.vo;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CourseVO {
  private Long id;
  private String name;
  private String instructor;
  private Integer capacity;
  private Integer availableSeats;
}
