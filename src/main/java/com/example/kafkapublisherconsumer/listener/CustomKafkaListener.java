package com.example.kafkapublisherconsumer.listener;


import com.example.kafkapublisherconsumer.stubs.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomKafkaListener {

    private final String TOPIC = "springboot";
    private final String TOPIC2= "springboot2";

    List<String> messages = new ArrayList<>();

    @org.springframework.kafka.annotation.KafkaListener(groupId = "javastring", topics = TOPIC, containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMessageFromTopic(String data){
        messages.add(data);
        return messages;
    }


    User userFromTopic =null;

    @org.springframework.kafka.annotation.KafkaListener(groupId = "javajson", topics = TOPIC2, containerFactory = "userKafkaListenerContainerFactory")
    public User getJsonMessageFromTopic(User data){
        userFromTopic=data;
        return userFromTopic;
    }

    public List<String> getMessages(){
        messages.stream().forEach(System.out::println);
        return messages;
    }

    public User getUser(){
        return userFromTopic;
    }


}
