package com.example.sendEmail.service;

import com.example.sendEmail.events.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountsEventsService {
    @Autowired
    private JavaMailSender javaMailSender;
    @KafkaListener(
            topics = "${topic.account.name:accounts}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1")
    public void consumer (Event<?> event){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("johnbernalsierra@gmail.com");
        message.setTo("johnbernalsierra@gmail.com");
        message.setSubject("Mensaje");
        message.setText("Cuerpo");

        javaMailSender.send(message);
    }

}
