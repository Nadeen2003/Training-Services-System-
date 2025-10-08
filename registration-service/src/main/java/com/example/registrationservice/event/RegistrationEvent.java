package com.example.registrationservice.event;

import java.time.LocalDateTime;

public class RegistrationEvent {
    private Long registrationId;
    private Long courseId;
    private String studentId;
    private LocalDateTime registeredAt;

    public RegistrationEvent() {}

    public RegistrationEvent(Long registrationId, Long courseId, String studentId, LocalDateTime registeredAt) {
        this.registrationId = registrationId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.registeredAt = registeredAt;
    }

    public Long getRegistrationId() { return registrationId; }
    public void setRegistrationId(Long registrationId) { this.registrationId = registrationId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }
}
