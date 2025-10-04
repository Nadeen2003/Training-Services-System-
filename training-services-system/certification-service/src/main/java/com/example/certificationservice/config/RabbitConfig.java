package com.example.certificationservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Value("${app.queues.courseCompleted}")
  private String courseCompletedQueue;

  @Value("${app.exchanges.attendance}")
  private String attendanceExchange;

  @Bean
  public Queue courseCompletedQueue(){ return new Queue(courseCompletedQueue, true); }

  @Bean
  public TopicExchange attendanceExchange(){ return new TopicExchange(attendanceExchange, true, false); }

  @Bean
  public Binding bindCompleted(Queue courseCompletedQueue, TopicExchange attendanceExchange){
    return BindingBuilder.bind(courseCompletedQueue).to(attendanceExchange).with("course.completed");
  }
}
