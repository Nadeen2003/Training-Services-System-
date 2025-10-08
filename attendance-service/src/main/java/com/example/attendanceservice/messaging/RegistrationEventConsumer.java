package com.example.attendanceservice.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationEventConsumer.class);

    @RabbitListener(queues = "registration.created")
    public void handleRegistrationCreated(Object event) {
        try {
            logger.info("Received registration.created event: {}", event);
            
            // Here you would typically parse the event object to extract registration details
            // For now, we'll just log that we received the event
            // In a real implementation, you might create an initial attendance record
            
            logger.info("Processed registration.created event successfully");
        } catch (Exception e) {
            logger.error("Failed to process registration.created event: {}", event, e);
        }
    }
}
