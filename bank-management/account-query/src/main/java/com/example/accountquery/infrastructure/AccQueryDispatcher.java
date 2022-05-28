package com.example.accountquery.infrastructure;

import com.example.cqrscore.domain.BaseEntity;
import com.example.cqrscore.infrastructure.QueryDispatcher;
import com.example.cqrscore.queries.BaseQuery;
import com.example.cqrscore.queries.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccQueryDispatcher implements QueryDispatcher {
    private final Map<Class<? extends BaseQuery> , List<QueryHandler>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandler<T> handler) {
        var handle = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handle.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handle = routes.get(query.getClass());
        if (handle == null || handle.size()<=0){
            throw new  RuntimeException("No Query handler was registered ! ");
        }
        if (handle.size()>1){
            throw new RuntimeException("Cannot send query to more than one  ! ");
        }
        return handle.get(0).handle(query);

    }
}
