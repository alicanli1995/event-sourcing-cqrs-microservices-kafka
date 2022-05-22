package com.example.cqrscore.commands;

import com.example.cqrscore.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {

    public BaseCommand(String id){
        super(id);
    }

}
