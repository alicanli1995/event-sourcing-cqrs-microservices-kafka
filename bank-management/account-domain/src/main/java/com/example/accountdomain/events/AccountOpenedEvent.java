package com.example.accountdomain.events;

import com.example.accountdomain.dto.AccountType;
import com.example.cqrscore.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {

    private String accountHolder;
    private AccountType accountType;
    private Date createdDate;
    private BigDecimal openingBalance;

}
