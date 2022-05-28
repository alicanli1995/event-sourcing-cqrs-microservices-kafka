package com.example.accountcmd.domain;

import com.example.accountcmd.api.commands.OpenAccountCommand;
import com.example.accountdomain.events.AccountClosedEvent;
import com.example.accountdomain.events.AccountOpenedEvent;
import com.example.accountdomain.events.FundsDepositedEvent;
import com.example.accountdomain.events.FundsWithdrawedEvent;
import com.example.cqrscore.domain.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor

public class AccountAggregate extends AggregateRoot {


    @Getter
    @Setter
    private Boolean isActive;
    @Getter
    @Setter
    private BigDecimal balance;

    public AccountAggregate(OpenAccountCommand command){
        raiseEvent(AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createdDate(new Date())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance())
                .build());
    }

    public void apply(AccountOpenedEvent event){
        this.id = event.getId();
        this.isActive = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(BigDecimal amount){
        if(Boolean.FALSE.equals(this.isActive)){
            throw new IllegalStateException("Funds can not be deposit closed bank acc !");
        }
        if(amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalStateException("Amount must be greater than zero  ! ");
        raiseEvent(FundsDepositedEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundsDepositedEvent event){
        this.id = event.getId();
        this.balance = this.balance.add(event.getAmount());
    }

    public void withdrawFunds(BigDecimal amount){
        if(Boolean.FALSE.equals(this.isActive)){
            throw new IllegalStateException("Funds can not be withdraw closed bank acc !");
        }
        raiseEvent(FundsWithdrawedEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }


    public void apply(FundsWithdrawedEvent event){
        this.id = event.getId();
        this.balance = this.balance.subtract(event.getAmount());
    }

    public void closeAccount(){
        if(Boolean.FALSE.equals(this.isActive)){
            throw new IllegalStateException("Bank account is already closed !");
        }
        raiseEvent(AccountClosedEvent.builder()
                .id(this.id)
                .build());

    }
    public void apply(AccountClosedEvent event){
        this.id = event.getId();
        this.isActive = false;
    }

}
