package com.example.accountquery.domain;

import com.example.accountdomain.dto.AccountType;
import com.example.cqrscore.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount extends BaseEntity {

    @Id
    private String id;

    private String accountHolder;

    private Date creationDate;

    private AccountType accountType;

    private BigDecimal balance;
}
