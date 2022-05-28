package com.example.cqrscore.queries;

import com.example.cqrscore.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandler<T extends BaseQuery> {

    List<BaseEntity> handle(T query);

}
