package com.streamService;


import com.entity.AuthAuditEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {


    @KafkaListener(topics = "my-topic", groupId = "my-auth")
    public void consumeAuthEvent(AuthAuditEvent event){
        //log.info("Audit kafka consumer event");
        System.out.println("Audit log received "+event);
    }

}
