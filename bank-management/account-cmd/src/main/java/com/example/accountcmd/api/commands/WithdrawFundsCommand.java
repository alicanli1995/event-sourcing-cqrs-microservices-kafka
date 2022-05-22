package com.example.accountcmd.api.commands;

import com.example.cqrscore.commands.BaseCommand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawFundsCommand extends BaseCommand {

    private BigDecimal amount;

}
