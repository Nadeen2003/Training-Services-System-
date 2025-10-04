package com.example.registrationservice.service;

import com.example.registrationservice.model.Registration;
import com.example.registrationservice.repository.RegistrationRepository;
import com.example.registrationservice.vo.CourseVO;
import com.example.registrationservice.vo.RegistrationWithCourse;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {
  private final RegistrationRepository repo;
  private final RestTemplate rest;
  private final RabbitTemplate rabbit;
  private final TopicExchange exchange;

  @Value("${spring.application.name}")
  private String appName;

  public RegistrationService(RegistrationRepository repo, RestTemplate rest, RabbitTemplate rabbit, TopicExchange exchange) {
    this.repo = repo;
    this.rest = rest;
    this.rabbit = rabbit;
    this.exchange = exchange;
  }

  public List<Registration> getAll(){ return repo.findAll(); }
  public Registration getById(Long id){ return repo.findById(id).orElse(null); }

  public Registration add(Registration r){
    r.setStatus("CREATED");
    Registration saved = repo.save(r);
    // Publish event for Attendance service
    rabbit.convertAndSend(exchange.getName(), "user.registered",
        Map.of("registrationId", saved.getId(), "courseId", saved.getCourseId(), "studentName", saved.getStudentName()));
    return saved;
  }

  public RegistrationWithCourse getWithCourse(Long id){
    Registration reg = getById(id);
    if (reg == null) return null;
    CourseVO course = rest.getForObject("http://localhost:9001/courses/" + reg.getCourseId(), CourseVO.class);
    return new RegistrationWithCourse(reg, course);
  }
}
