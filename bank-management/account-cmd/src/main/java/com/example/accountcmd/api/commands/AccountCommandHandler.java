package com.example.accountcmd.api.commands;

import com.example.accountcmd.domain.AccountAggregate;
import com.example.cqrscore.handlers.EventSourcingHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountCommandHandler implements CommandHandler{

    private final EventSourcingHandler<AccountAggregate> handler;

    @Override
    public void handle(OpenAccountCommand command) {
        var aggregate = new AccountAggregate(command);
        handler.save(aggregate);
    }

    @Override
    public void handle(DepositFundsCommand command) {
        var aggregate = handler.getById(command.getId());
        aggregate.depositFunds(command.getAmount());
        handler.save(aggregate);
    }

    @Override
    public void handle(WithdrawFundsCommand command) {
        var aggregate = handler.getById(command.getId());
        if(command.getAmount().compareTo(aggregate.getBalance()) > 0){
            throw new IllegalStateException("Withdrawal declined , not enough amount fund! ");
        }
        aggregate.withdrawFunds(command.getAmount());
        handler.save(aggregate);
    }

    @Override
    public void handle(CloseAccuntCommand command) {
        var aggregate = handler.getById(command.getId());
        aggregate.closeAccount();
        handler.save(aggregate);
    }
}
