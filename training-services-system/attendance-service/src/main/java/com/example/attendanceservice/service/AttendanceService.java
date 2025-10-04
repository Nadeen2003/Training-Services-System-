package com.example.attendanceservice.service;

import com.example.attendanceservice.model.Attendance;
import com.example.attendanceservice.repository.AttendanceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {
  private final AttendanceRepository repo;
  private final RabbitTemplate rabbit;
  private final String attendanceExchange;

  public AttendanceService(AttendanceRepository repo, RabbitTemplate rabbit,
                           @Value("${app.exchanges.attendance}") String attendanceExchange){
    this.repo = repo;
    this.rabbit = rabbit;
    this.attendanceExchange = attendanceExchange;
  }

  public List<Attendance> all(){ return repo.findAll(); }

  @RabbitListener(queues = "${app.queues.registrationCreated}")
  public void consumeRegisteredEvent(Map<String, Object> payload){
    Long registrationId = ((Number)payload.get("registrationId")).longValue();
    Long courseId = ((Number)payload.get("courseId")).longValue();
    String studentName = (String) payload.get("studentName");
    Attendance a = new Attendance(null, registrationId, courseId, studentName, "STARTED");
    repo.save(a);
  }

  public Attendance complete(Long id){
    var a = repo.findById(id).orElse(null);
    if (a == null) return null;
    a.setStatus("COMPLETED");
    var saved = repo.save(a);
    rabbit.convertAndSend(attendanceExchange, "course.completed",
        Map.of("registrationId", saved.getRegistrationId(), "courseId", saved.getCourseId(), "studentName", saved.getStudentName()));
    return saved;
  }
}
