package com.example.accountcmd;

import com.example.accountcmd.api.commands.*;
import com.example.cqrscore.infrastructure.CommandDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AccountCmdApplication {

    @Autowired
    private  CommandDispatcher commandDispatcher;

    @Autowired
    private  CommandHandler handler;

    public static void main(String[] args) {
        SpringApplication.run(AccountCmdApplication.class, args);
    }

    @PostConstruct
    public void registerHandlers(){
        commandDispatcher.registerHandler(OpenAccountCommand.class, handler::handle);
        commandDispatcher.registerHandler(DepositFundsCommand.class, handler::handle);
        commandDispatcher.registerHandler(WithdrawFundsCommand.class, handler::handle);
        commandDispatcher.registerHandler(CloseAccuntCommand.class, handler::handle);
        commandDispatcher.registerHandler(RestoreReadDbCommand.class, handler::handle);
    }
}
