package com.example.attendanceservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AttendancePublishConfig {

  @Value("${app.exchanges.attendance}")
  private String attendanceExchange;

  @Bean
  public TopicExchange attendanceExchange(){
    return new TopicExchange(attendanceExchange, true, false);
  }
}
