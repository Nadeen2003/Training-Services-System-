package com.example.courseservice.service;

import com.example.courseservice.model.Course;
import com.example.courseservice.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
  private final CourseRepository repo;
  public CourseService(CourseRepository repo) { this.repo = repo; }

  public List<Course> getAll(){ return repo.findAll(); }
  public Course getById(Long id){ return repo.findById(id).orElse(null); }
  public Course add(Course c){ return repo.save(c); }
}
