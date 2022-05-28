package com.example.cqrscore.producers;

import com.example.cqrscore.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
