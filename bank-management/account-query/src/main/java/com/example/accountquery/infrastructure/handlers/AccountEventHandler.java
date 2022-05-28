package com.example.accountquery.infrastructure.handlers;

import com.example.accountdomain.events.AccountClosedEvent;
import com.example.accountdomain.events.AccountOpenedEvent;
import com.example.accountdomain.events.FundsDepositedEvent;
import com.example.accountdomain.events.FundsWithdrawedEvent;
import com.example.accountquery.domain.AccountRepository;
import com.example.accountquery.domain.BankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
@RequiredArgsConstructor

public class AccountEventHandler implements EventHandler{

    private final AccountRepository accountRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        var bnkAcc = BankAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .creationDate(new Date())
                .build();
        accountRepository.save(bnkAcc);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        var bnkAcc = accountRepository.findById(event.getId());
        if(bnkAcc.isEmpty()) return;
        var currentBalance = bnkAcc.get().getBalance();
        var resultBalance = currentBalance.add(event.getAmount());
        bnkAcc.get().setBalance(resultBalance);
        accountRepository.save(bnkAcc.get());
    }

    @Override
    public void on(FundsWithdrawedEvent event) {
        var bnkAcc = accountRepository.findById(event.getId());
        if(bnkAcc.isEmpty()) return;
        var currentBalance = bnkAcc.get().getBalance();
        if(currentBalance.compareTo(event.getAmount()) > 0){
            var resultBalance = currentBalance.subtract(event.getAmount());
            bnkAcc.get().setBalance(resultBalance);
            accountRepository.save(bnkAcc.get());
        }
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());

    }
}
