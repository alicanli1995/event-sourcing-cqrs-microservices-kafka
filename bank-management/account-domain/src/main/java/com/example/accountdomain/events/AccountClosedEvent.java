package com.example.accountdomain.events;


import com.example.cqrscore.events.BaseEvent;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AccountClosedEvent extends BaseEvent {
}
