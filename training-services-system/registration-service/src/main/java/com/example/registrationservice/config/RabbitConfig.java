package com.example.registrationservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Value("${app.exchanges.registration}")
  private String registrationExchange;

  @Bean
  public TopicExchange registrationExchange(){
    return new TopicExchange(registrationExchange, true, false);
  }
}
