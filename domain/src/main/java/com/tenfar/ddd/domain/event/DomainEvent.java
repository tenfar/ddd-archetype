
package com.tenfar.ddd.domain.event;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.util.UUID;


@ToString
@Getter
public abstract class DomainEvent extends ApplicationEvent {

    private final UUID eventId;
    private final Instant occurredOn;

    public DomainEvent(Object source) {
        super(source);
        this.eventId = UUID.randomUUID();
        this.occurredOn = Instant.now();
    }
}