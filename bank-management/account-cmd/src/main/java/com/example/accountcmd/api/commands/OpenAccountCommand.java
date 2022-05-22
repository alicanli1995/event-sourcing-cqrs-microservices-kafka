package com.example.accountcmd.api.commands;

import com.example.accountdomain.dto.AccountType;
import com.example.cqrscore.commands.BaseCommand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private BigDecimal openingBalance;
}
