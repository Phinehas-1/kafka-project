package com.bigdecimal.kafkaproducer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigdecimal.kafkaproducer.dto.Message;
import com.bigdecimal.kafkaproducer.service.MessagePublisher;


@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessagePublisher messagePublisher;

    public MessageController(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }
    @PostMapping("/send")
    public ResponseEntity<?> postMethodName(@RequestBody Message message) {
        try {
            messagePublisher.sendMessage(message);
            return ResponseEntity.ok().body("Sent message successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error occured while sending message.");
        }
    }
    
}
