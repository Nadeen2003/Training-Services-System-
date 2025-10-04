package com.example.courseservice.model;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;       // اسم الكورس
    private String instructor; // اسم الدكتور
    private int capacity;      // السعة

    public Course() { }

    public Course(String name, String instructor, int capacity) {
        this.name = name;
        this.instructor = instructor;
        this.capacity = capacity;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
