package com.tenfar.ddd.application.events.subscribers;

import com.tenfar.ddd.application.events.handlers.EventHandler;
import com.tenfar.ddd.application.events.handlers.EventHandlerFactory;
import com.tenfar.ddd.domain.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventSubscriber {
    private final EventHandlerFactory eventHandlerFactory;

    @Autowired
    public EventSubscriber(EventHandlerFactory eventHandlerFactory) {
        this.eventHandlerFactory = eventHandlerFactory;
    }

    @EventListener
    public void handleEvent(DomainEvent event) {
        EventHandler<DomainEvent> handler = eventHandlerFactory.getHandlerForEvent(event);
        handler.handle(event);
    }
}