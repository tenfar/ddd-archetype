package com.tenfar.ddd.application.events.handlers;

import com.tenfar.ddd.application.events.annotations.EventHandlerFor;
import com.tenfar.ddd.domain.event.DomainEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventHandlerFactory {

    private final Map<Class<? extends DomainEvent>, EventHandler<DomainEvent>> handlerMap = new HashMap<>();
    private final ApplicationContext applicationContext;

    public EventHandlerFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, Object> handlers = applicationContext.getBeansWithAnnotation(EventHandlerFor.class);
        for (Object handler : handlers.values()) {
            if (handler instanceof EventHandler) {
                EventHandlerFor annotation = handler.getClass().getAnnotation(EventHandlerFor.class);
                if (annotation != null) {
                    handlerMap.put(annotation.value(), (EventHandler<DomainEvent>) handler);
                }
            }
        }
    }

    public EventHandler<DomainEvent> getHandlerForEvent(DomainEvent event) {
        return handlerMap.get(event.getClass());
    }
}