package com.tenfar.ddd.domain.table.events;

import com.tenfar.ddd.domain.table.vo.CustomerId;
import com.tenfar.ddd.domain.table.vo.ReservationDate;
import com.tenfar.ddd.domain.table.vo.ReservationDuration;
import com.tenfar.ddd.domain.event.DomainEvent;
import lombok.*;

@ToString(callSuper = true)
@Getter
@EqualsAndHashCode(callSuper = true)
public class TableReservedEvent extends DomainEvent {
    private final String tableId;
    private final CustomerId customerId;
    private final ReservationDate reservationDate;
    private final ReservationDuration duration;

    public TableReservedEvent(Object source, String tableId, CustomerId customerId, ReservationDate reservationDate, ReservationDuration duration ) {
        super(source);
        this.tableId = tableId;
        this.customerId = customerId;
        this.reservationDate = reservationDate;
        this.duration = duration;
    }
}
