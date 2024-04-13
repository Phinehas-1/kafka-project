package com.bigdecimal.kafkaproducer.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.bigdecimal.kafkaproducer.dto.Message;

@Service
public class MessagePublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.messaging.topic}")
    private String topic;

    public MessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, exception) -> {
            if(exception == null){
                System.out.println("Sent message : "+result.getProducerRecord().value().toString());
            }else{
                System.out.println("Error sending message : ");
                exception.printStackTrace();
            }
        });
    }
}
