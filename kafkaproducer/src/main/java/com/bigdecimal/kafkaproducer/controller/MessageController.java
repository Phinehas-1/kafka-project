package com.bigdecimal.kafkaproducer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigdecimal.kafkaproducer.service.MessagePublisher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessagePublisher messagePublisher;

    public MessageController(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }
    @PostMapping("/send/{message}")
    public ResponseEntity<?> postMethodName(@PathVariable String message) {
        try {
            messagePublisher.sendMessage(message);
            return ResponseEntity.ok().body("Sent message successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error occured while sending message.");
        }
    }
    
}
