package com.example.accountquery.api.controller;

import com.example.accountquery.api.dto.AccountLookupResponse;
import com.example.accountquery.api.queries.FindAccByHolderIdQuery;
import com.example.accountquery.api.queries.FindAccByIdQuery;
import com.example.accountquery.api.queries.FindAccWithBalanceQuery;
import com.example.accountquery.api.queries.FindAllAccQuery;
import com.example.accountquery.domain.BankAccount;
import com.example.cqrscore.infrastructure.QueryDispatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestScope
@CrossOrigin
@Validated
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/accountLookup")
public class AccountLookupController {

    private final QueryDispatcher queryDispatcher;

    @GetMapping("/")
    public ResponseEntity<AccountLookupResponse> getAllAcc(){
        try {
            List<BankAccount> accounts = queryDispatcher.send(new FindAllAccQuery());
            if (accounts.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            var response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Successfully returned accounts ... ")
                    .build();

            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Failed to complete get all accounts requests !";
            log.error(safeError);
            return new ResponseEntity<>(new AccountLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountLookupResponse> getAccById(@PathVariable("id") String id){
        try {
            List<BankAccount> accounts = queryDispatcher.send(new FindAccByIdQuery(id));
            if (accounts.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            var response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Successfully returned account ... ")
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Failed to complete get account by id requests !";
            log.error(safeError);
            return new ResponseEntity<>(new AccountLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/byholder/{accountHolder}")
    public ResponseEntity<AccountLookupResponse> getAccByHolderId(@PathVariable("accountHolder") String accountHolder){
        try {
            List<BankAccount> accounts = queryDispatcher.send(new FindAccByHolderIdQuery(accountHolder));
            if (accounts.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            var response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Successfully returned account ... ")
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Failed to complete get account by id requests !";
            log.error(safeError);
            return new ResponseEntity<>(new AccountLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/withBalance/{highBalance}/{lowBalance}")
    public ResponseEntity<AccountLookupResponse> getAccListByBalance(
            @PathVariable("highBalance") BigDecimal highBalance,
            @PathVariable("lowBalance") BigDecimal lowBalance){
        try {
            List<BankAccount> accounts = queryDispatcher.send(new FindAccWithBalanceQuery(highBalance,lowBalance));
            if (accounts.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            var response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Successfully returned account ... ")
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Failed to complete get account by id requests !";
            log.error(safeError);
            return new ResponseEntity<>(new AccountLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    }
