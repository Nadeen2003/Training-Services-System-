package com.example.registrationservice.controller;

import com.example.registrationservice.event.RegistrationEvent;
import com.example.registrationservice.messaging.RegistrationEventPublisher;
import com.example.registrationservice.model.Registration;
import com.example.registrationservice.repo.RegistrationRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationRepository repo;
    private final RegistrationEventPublisher eventPublisher;

    public RegistrationController(RegistrationRepository repo, RegistrationEventPublisher eventPublisher) {
        this.repo = repo;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        List<Registration> registrations = repo.findAll();
        return ResponseEntity.ok(registrations);
    }

    @PostMapping
    public ResponseEntity<Registration> createRegistration(@Valid @RequestBody Registration registration) {
        Registration savedRegistration = repo.save(registration);
        
        // Publish event
        RegistrationEvent event = new RegistrationEvent(
            savedRegistration.getId(),
            savedRegistration.getCourseId(),
            savedRegistration.getStudentId(),
            savedRegistration.getRegisteredAt()
        );
        eventPublisher.publishRegistrationCreated(event);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRegistration);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistration(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long id, @Valid @RequestBody Registration registration) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        registration.setId(id);
        Registration updatedRegistration = repo.save(registration);
        return ResponseEntity.ok(updatedRegistration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
