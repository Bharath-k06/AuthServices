package com.streamService;


import com.entity.AuthAuditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthLogProducer {

    private  final String TOPIC = "my-topic";

    @Autowired
    private KafkaTemplate<String, AuthAuditEvent> kafkaTemplate;

    public void sendAuditEvent(AuthAuditEvent event){
        kafkaTemplate.send(TOPIC,event);
    }
}
