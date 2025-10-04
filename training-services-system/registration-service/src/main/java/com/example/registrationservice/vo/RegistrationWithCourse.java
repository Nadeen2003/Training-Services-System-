package com.example.registrationservice.vo;

import com.example.registrationservice.model.Registration;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegistrationWithCourse {
  private Registration registration;
  private CourseVO course;
}
