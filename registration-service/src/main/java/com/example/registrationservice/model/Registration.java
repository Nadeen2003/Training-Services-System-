package com.example.registrationservice.model;

import jakarta.persistence.*;

@Entity
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long courseId;
    private String studentName;

    public Registration(){}
    public Registration(Long courseId, String studentName){ this.courseId = courseId; this.studentName = studentName; }

    public Long getId(){ return id; }
    public Long getCourseId(){ return courseId; }
    public void setCourseId(Long courseId){ this.courseId = courseId; }
    public String getStudentName(){ return studentName; }
    public void setStudentName(String studentName){ this.studentName = studentName; }
}
