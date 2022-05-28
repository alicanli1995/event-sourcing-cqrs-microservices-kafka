package com.example.accountquery.infrastructure.consumers;

import com.example.accountdomain.events.AccountClosedEvent;
import com.example.accountdomain.events.AccountOpenedEvent;
import com.example.accountdomain.events.FundsDepositedEvent;
import com.example.accountdomain.events.FundsWithdrawedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);
    void consume(@Payload FundsDepositedEvent event, Acknowledgment ack);
    void consume(@Payload FundsWithdrawedEvent event, Acknowledgment ack);
    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
