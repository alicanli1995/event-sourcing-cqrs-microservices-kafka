package com.example.accountdomain.events;

import com.example.cqrscore.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FundsDepositedEvent extends BaseEvent {

    private BigDecimal amount;

}
