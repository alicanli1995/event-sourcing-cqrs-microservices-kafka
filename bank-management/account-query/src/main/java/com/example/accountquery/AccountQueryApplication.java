package com.example.accountquery;

import com.example.accountquery.api.queries.*;
import com.example.cqrscore.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AccountQueryApplication {

    @Autowired
    private QueryDispatcher queryDispatcher;

    @Autowired
    private QueryHandler queryHandler;

    public static void main(String[] args) {
        SpringApplication.run(AccountQueryApplication.class, args);
    }

    @PostConstruct
    public void registerHandler(){
        queryDispatcher.registerHandler(FindAllAccQuery.class,queryHandler::handle);
        queryDispatcher.registerHandler(FindAccWithBalanceQuery.class,queryHandler::handle);
        queryDispatcher.registerHandler(FindAccByHolderIdQuery.class,queryHandler::handle);
        queryDispatcher.registerHandler(FindAccByIdQuery.class,queryHandler::handle);
    }

}
