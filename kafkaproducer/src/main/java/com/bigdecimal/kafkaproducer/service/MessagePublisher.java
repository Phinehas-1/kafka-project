package com.bigdecimal.kafkaproducer.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.messaging.topic}")
    private String topic;

    public MessagePublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, exception) -> {
            if(exception == null){
                System.out.println("Sent message : "+result.getProducerRecord().value());
            }else{
                System.out.println("Error sending message : ");
                exception.printStackTrace();
            }
        });
    }
}
