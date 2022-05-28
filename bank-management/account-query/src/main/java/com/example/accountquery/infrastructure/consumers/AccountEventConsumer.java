package com.example.accountquery.infrastructure.consumers;

import com.example.accountdomain.events.AccountClosedEvent;
import com.example.accountdomain.events.AccountOpenedEvent;
import com.example.accountdomain.events.FundsDepositedEvent;
import com.example.accountdomain.events.FundsWithdrawedEvent;
import com.example.accountquery.infrastructure.handlers.EventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountEventConsumer implements EventConsumer{

    private final EventHandler eventHandler;

    @KafkaListener(topics = "AccountOpenedEvent",groupId = "bankaccConsumer")
    @Override
    public void consume(@Payload AccountOpenedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsDepositedEvent",groupId = "bankaccConsumer")
    @Override
    public void consume(@Payload FundsDepositedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsWithdrawedEvent",groupId = "bankaccConsumer")
    @Override
    public void consume(@Payload FundsWithdrawedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent",groupId = "bankaccConsumer")
    @Override
    public void consume(@Payload AccountClosedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
