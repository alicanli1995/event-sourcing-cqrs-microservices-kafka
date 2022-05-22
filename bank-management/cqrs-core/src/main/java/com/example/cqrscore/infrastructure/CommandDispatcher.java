package com.example.cqrscore.infrastructure;

import com.example.cqrscore.commands.BaseCommand;
import com.example.cqrscore.commands.CommandHandler;

public interface CommandDispatcher {

    <T extends BaseCommand> void registerHandler(Class<T> type , CommandHandler<T> handler);
    void send(BaseCommand command);

}
