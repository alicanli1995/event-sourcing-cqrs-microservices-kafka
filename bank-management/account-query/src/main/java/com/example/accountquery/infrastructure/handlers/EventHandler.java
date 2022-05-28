package com.example.accountquery.infrastructure.handlers;

import com.example.accountdomain.events.AccountClosedEvent;
import com.example.accountdomain.events.AccountOpenedEvent;
import com.example.accountdomain.events.FundsDepositedEvent;
import com.example.accountdomain.events.FundsWithdrawedEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawedEvent event);
    void on(AccountClosedEvent event);
}
