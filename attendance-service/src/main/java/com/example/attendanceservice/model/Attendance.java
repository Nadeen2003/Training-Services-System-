package com.example.attendanceservice.model;

import jakarta.persistence.*;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long registrationId; // رقم التسجيل المرتبط
    private String status;       // PRESENT / ABSENT

    public Attendance() {}

    public Attendance(Long registrationId, String status) {
        this.registrationId = registrationId;
        this.status = status;
    }

    public Long getId() { return id; }

    public Long getRegistrationId() { return registrationId; }
    public void setRegistrationId(Long registrationId) { this.registrationId = registrationId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
