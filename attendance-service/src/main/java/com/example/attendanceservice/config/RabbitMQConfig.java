package com.example.attendanceservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange trainingEventsExchange() {
        return new TopicExchange("training.events");
    }

    @Bean
    public Queue registrationCreatedQueue() {
        return QueueBuilder.durable("registration.created").build();
    }

    @Bean
    public Binding registrationCreatedBinding() {
        return BindingBuilder
                .bind(registrationCreatedQueue())
                .to(trainingEventsExchange())
                .with("registration.created");
    }
}
