package com.example.accountcmd.infrastructure;

import com.example.accountcmd.domain.AccountAggregate;
import com.example.cqrscore.domain.AggregateRoot;
import com.example.cqrscore.events.BaseEvent;
import com.example.cqrscore.handlers.EventSourcingHandler;
import com.example.cqrscore.infrastructure.EventStore;
import com.example.cqrscore.producers.EventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    private final EventStore eventStore;
    private final EventProducer eventProducer;

    @Override
    public void save(AggregateRoot aggregateRoot) {
        eventStore.saveEvent(aggregateRoot.getId(),aggregateRoot.getUncomittedChanges(), aggregateRoot.getVersion());
        aggregateRoot.markAsCommitted();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new AccountAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()){
            aggregate.replayEvents(events);
            var latestVersion = events.stream()
                    .map(BaseEvent::getVersion)
                    .max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

    @Override
    public void republishEvents() {
        var aggregateIds = eventStore.getAggregateIds();
        for (var  aggregateId : aggregateIds){
            var aggregate = getById(aggregateId);
            if (!aggregate.getIsActive()) continue;;
            var events = eventStore.getEvents(aggregateId);
            for (var event : events){
                eventProducer.produce(event.getClass().getSimpleName(),event);
            }
        }
    }
}
