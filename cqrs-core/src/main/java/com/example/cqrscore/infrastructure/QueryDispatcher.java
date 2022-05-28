package com.example.cqrscore.infrastructure;

import com.example.cqrscore.domain.BaseEntity;
import com.example.cqrscore.queries.BaseQuery;
import com.example.cqrscore.queries.QueryHandler;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandler<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
