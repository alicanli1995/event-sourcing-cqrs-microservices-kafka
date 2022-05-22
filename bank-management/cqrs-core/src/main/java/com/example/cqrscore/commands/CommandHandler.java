package com.example.cqrscore.commands;

@FunctionalInterface
public interface CommandHandler<T extends BaseCommand> {

    void handle(T command);

}
