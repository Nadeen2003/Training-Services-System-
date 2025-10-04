package com.example.attendanceservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Value("${app.queues.registrationCreated}")
  private String registrationCreatedQueue;

  @Value("${app.exchanges.registration}")
  private String registrationExchange;

  @Bean
  public Queue registrationCreatedQueue(){ return new Queue(registrationCreatedQueue, true); }

  @Bean
  public TopicExchange registrationExchange(){ return new TopicExchange(registrationExchange, true, false); }

  @Bean
  public Binding bindRegistrationCreated(Queue registrationCreatedQueue, TopicExchange registrationExchange){
    return BindingBuilder.bind(registrationCreatedQueue).to(registrationExchange).with("user.registered");
  }
}
