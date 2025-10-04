package com.example.courseservice;

import com.example.courseservice.model.Course;
import com.example.courseservice.repo.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private final CourseRepository repo;

    public InitData(CourseRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Course("Web Dev", "Dr A", 40));
        repo.save(new Course("Data Science", "Dr B", 30));
    }
}
