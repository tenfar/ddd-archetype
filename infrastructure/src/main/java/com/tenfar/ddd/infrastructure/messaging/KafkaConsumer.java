
package com.tenfar.ddd.infrastructure.messaging;

import com.tenfar.ddd.domain.event.DomainEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "#{@kafkaTopic}", groupId = "#{@kafkaGroupId}")
    public void handleEvent(DomainEvent event) {
        // 处理事件
        System.out.println("Received event: " + event);
    }
}