package com.example.accountcmd.api.commands;

public interface CommandHandler {
    void handle(OpenAccountCommand command);
    void handle(DepositFundsCommand command);
    void handle(WithdrawFundsCommand command);
    void handle(CloseAccuntCommand command);
    void handle(RestoreReadDbCommand command);


}
