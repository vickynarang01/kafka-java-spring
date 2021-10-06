package com.example.kafkapublisherconsumer.controller;

import com.example.kafkapublisherconsumer.listener.CustomKafkaListener;
import com.example.kafkapublisherconsumer.stubs.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, Object> template;
    
    @Autowired
    CustomKafkaListener kafkaListener;

    private final String TOPIC = "springboot";
    private final String TOPIC2= "springboot2";
    List<String> messages = new ArrayList<>();
    User userFromTopic =null;


    @GetMapping("/publish/{name}")
    public String publishMessage(@PathVariable String name){

        template.send(TOPIC,"Hi "+name+" this is Kafka Spring Boot");
        return "Data Published";
    }

    @GetMapping("/publishJson")
    public String publishMessage(){
        User user=new User(1,"User1", new String[]{"address","address2"});
        template.send(TOPIC2,user);
        return "Json Data Publihed";
    }

    @GetMapping("/consumeString")
    public List<String> consumeMessage(){
        return kafkaListener.getMessages();
    }

    @GetMapping("/consumeJson")
    public User consumeJsonMessage(){
        return kafkaListener.getUser();
    }





}
