package com.example.accountquery.api.queries;

import com.example.accountquery.domain.AccountRepository;
import com.example.accountquery.domain.BankAccount;
import com.example.cqrscore.domain.BaseEntity;
import com.example.cqrscore.infrastructure.QueryDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountQueryHandler implements QueryHandler{

    private final AccountRepository accountRepository;

    @Override
    public List<BaseEntity> handle(FindAllAccQuery query) {
        Iterable< BankAccount> bankAccounts = accountRepository.findAll();
        List<BaseEntity> baseEntityList = new ArrayList<>();
        bankAccounts.forEach(baseEntityList::add);
        return baseEntityList;
    }

    @Override
    public List<BaseEntity> handle(FindAccByIdQuery query) {
        var bankAcc = accountRepository.findById(query.getId());
        List<BaseEntity> bankAccList = new ArrayList<>();
        bankAcc.ifPresent(bankAccList::add);
        return bankAccList;
    }

    @Override
    public List<BaseEntity> handle(FindAccByHolderIdQuery query) {
        var bankAcc = accountRepository.findByAccountHolder(query.getAccountHolder());
        List<BaseEntity> bankAccList = new ArrayList<>();
        bankAcc.ifPresent(bankAccList::add);
        return bankAccList;
    }

    @Override
    public List<BaseEntity> handle(FindAccWithBalanceQuery query) {
        var bankAcc = accountRepository.findByBalanceGreaterThanAndBalanceLessThan(query.getHighBalance(),query.getLessBalance());
        if(!bankAcc.isEmpty())
            return new ArrayList<>(bankAcc);
        else return new ArrayList<>();
    }
}
