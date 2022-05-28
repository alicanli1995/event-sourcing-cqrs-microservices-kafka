package com.example.cqrscore.handlers;

import com.example.cqrscore.domain.AggregateRoot;

public interface EventSourcingHandler<T> {

    void save(AggregateRoot aggregateRoot);
    T getById(String id);

    void republishEvents();
}
