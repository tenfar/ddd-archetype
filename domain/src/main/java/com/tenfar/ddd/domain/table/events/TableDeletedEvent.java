package com.tenfar.ddd.domain.table.events;

import com.tenfar.ddd.common.utils.UuidGenerator;
import com.tenfar.ddd.domain.event.DomainEvent;
import com.tenfar.ddd.domain.table.entities.Table;
import lombok.Getter;

@Getter
public class TableDeletedEvent extends DomainEvent {
    private final Table table;

    public TableDeletedEvent(Object source, UuidGenerator uuidGenerator, Table table) {
        super(source, uuidGenerator);
        this.table = table;
    }

}