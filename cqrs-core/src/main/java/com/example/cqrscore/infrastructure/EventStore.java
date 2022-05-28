package com.example.cqrscore.infrastructure;

import com.example.cqrscore.events.BaseEvent;

import java.util.List;

public interface EventStore {
    void saveEvent(String aggregateId , Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(String aggregateId);
    List<String> getAggregateIds();
}
