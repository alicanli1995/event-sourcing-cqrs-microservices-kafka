package com.example.accountcmd.api.controller;

import com.example.accountcmd.api.commands.OpenAccountCommand;
import com.example.accountcmd.api.dto.OpenAccResponse;
import com.example.accountdomain.dto.BaseResponse;
import com.example.cqrscore.infrastructure.CommandDispatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.text.MessageFormat;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestScope
@RequestMapping(path = "/api/v1/openBankAccount")
@RequiredArgsConstructor
@Log4j2
@Validated
public class OpenAccController {

    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openBankAccount(@RequestBody @Validated OpenAccountCommand command){
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new OpenAccResponse
                    ("Bank account open successfully" , id), HttpStatus.CREATED);
        }catch (IllegalStateException e){
            log.warn("Client made a bad request -> " + e);
            return new ResponseEntity<>(new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var errorMsg = MessageFormat.format("Error while processing request to open a new bank account for id -> {0}" , id);
            log.warn(errorMsg + e.getMessage());
            return new ResponseEntity<>(new OpenAccResponse(errorMsg,id),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
