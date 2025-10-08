package com.example.registrationservice.messaging;

import com.example.registrationservice.event.RegistrationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventPublisher {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationEventPublisher.class);
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishRegistrationCreated(RegistrationEvent event) {
        try {
            rabbitTemplate.convertAndSend("training.events", "registration.created", event);
            logger.info("Published registration.created event: {}", event);
        } catch (Exception e) {
            logger.error("Failed to publish registration.created event: {}", event, e);
        }
    }
}
