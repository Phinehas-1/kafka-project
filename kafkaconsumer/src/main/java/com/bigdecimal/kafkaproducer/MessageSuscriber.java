package com.bigdecimal.kafkaproducer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bigdecimal.kafkaproducer.dto.Message;

@Service
public class MessageSuscriber {

    @KafkaListener(topics = "topic-one", groupId = "group-one")
    public void consume(Message message){
        System.out.println("Consumed message: "+ message.toString());
    }
}
