package com.example.certificationservice.model;

import jakarta.persistence.*;

@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long registrationId;

    // true = ISSUED, false = NOT_ISSUED
    private boolean issued;

    public Certificate() {}

    public Certificate(Long registrationId, boolean issued) {
        this.registrationId = registrationId;
        this.issued = issued;
    }

    public Long getId() { return id; }
    public Long getRegistrationId() { return registrationId; }
    public void setRegistrationId(Long registrationId) { this.registrationId = registrationId; }
    public boolean isIssued() { return issued; }
    public void setIssued(boolean issued) { this.issued = issued; }
}
