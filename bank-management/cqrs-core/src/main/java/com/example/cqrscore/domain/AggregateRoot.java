package com.example.cqrscore.domain;

import com.example.cqrscore.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AggregateRoot {

    @Getter
    @Setter
    protected String id;

    @Setter
    @Getter
    private int version = 1;
    private final List<BaseEvent> changes = new ArrayList<>();

    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public List<BaseEvent> getUncomittedChanges(){
        return this.changes;
    }

    public void markAsCommitted(){
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, Boolean isNewEvent){
        try {
            var method = getClass().getDeclaredMethod("apply",event.getClass());
            method.setAccessible(true);
            method.invoke(this,event);

        } catch (NoSuchMethodException e){
            logger.log(Level.WARNING, MessageFormat.format("The apply method not found {0}",event.getClass().getName()));
        } catch (Exception e){
            logger.log(Level.SEVERE, "Error applying event to aggregate", e);
        } finally {
            if(Boolean.TRUE.equals(isNewEvent))
                changes.add(event);
        }

    }

    public void raiseEvent(BaseEvent event){
        applyChange(event,true);
    }

    public void replayEvents(Iterable<BaseEvent> events){
        events.forEach(event -> applyChange(event,false));
    }

}
