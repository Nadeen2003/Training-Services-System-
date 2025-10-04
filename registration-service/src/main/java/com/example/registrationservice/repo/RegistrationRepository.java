package com.example.registrationservice.repo;

import com.example.registrationservice.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {}
