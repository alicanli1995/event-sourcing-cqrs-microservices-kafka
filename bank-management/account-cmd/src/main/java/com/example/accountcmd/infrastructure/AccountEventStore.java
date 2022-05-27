package com.example.accountcmd.infrastructure;

import com.example.accountcmd.domain.AccountAggregate;
import com.example.accountcmd.domain.EventStoreRepository;
import com.example.cqrscore.events.BaseEvent;
import com.example.cqrscore.events.EventModel;
import com.example.cqrscore.exception.AggregateNotFoundException;
import com.example.cqrscore.exception.ConcurrencyException;
import com.example.cqrscore.infrastructure.EventStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AccountEventStore implements EventStore {

    private final EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() -1).getVersion() != expectedVersion){
            throw new ConcurrencyException();
        }
        var version = expectedVersion;
        for (var event :events){
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            if (persistedEvent != null)
            {
                // TODO : produce event on kafka ....
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null && eventStream.isEmpty()){
            throw new AggregateNotFoundException("Incorrect account Id provided ! ");
        }
        return eventStream.stream()
                .map(EventModel::getEventData)
                .toList();
    }
}
