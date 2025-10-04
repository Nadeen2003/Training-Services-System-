package com.example.attendanceservice.repo;

import com.example.attendanceservice.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
