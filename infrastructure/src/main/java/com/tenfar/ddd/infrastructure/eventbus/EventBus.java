package com.tenfar.ddd.infrastructure.eventbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.beans.EventHandler;

@Service
public class EventBus {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(String topic, Object event) {
        kafkaTemplate.send(topic, event);
    }

    public void subscribe(String topic, EventHandler handler) {
        // 这里可以添加 Kafka 消费者的逻辑
        // 例如，创建一个 KafkaListener 并注册到 Kafka 监听器容器工厂
    }
}
