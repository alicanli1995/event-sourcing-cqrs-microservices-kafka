package com.example.accountquery.domain;

import com.example.cqrscore.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<BankAccount,String > {

    Optional<BankAccount> findByAccountHolder(String accountHolder);

    List<BaseEntity> findByBalanceGreaterThanAndBalanceLessThan(BigDecimal balanceHigh, BigDecimal balanceLess);


}
