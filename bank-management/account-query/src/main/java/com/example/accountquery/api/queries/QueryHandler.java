package com.example.accountquery.api.queries;

import com.example.cqrscore.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllAccQuery query);
    List<BaseEntity> handle(FindAccByIdQuery query);
    List<BaseEntity> handle(FindAccByHolderIdQuery query);
    List<BaseEntity> handle(FindAccWithBalanceQuery query);
}
