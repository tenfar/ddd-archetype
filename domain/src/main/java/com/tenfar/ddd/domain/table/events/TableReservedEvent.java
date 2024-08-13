package com.tenfar.ddd.domain.table.events;

import com.tenfar.ddd.common.utils.UuidGenerator;
import com.tenfar.ddd.domain.event.DomainEvent;
import com.tenfar.ddd.domain.table.vo.CustomerId;
import com.tenfar.ddd.domain.table.vo.ReservationDate;
import com.tenfar.ddd.domain.table.vo.ReservationDuration;
import com.tenfar.ddd.domain.table.vo.TableId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@EqualsAndHashCode(callSuper = true)
public class TableReservedEvent extends DomainEvent {
    private final TableId tableId;
    private final CustomerId customerId;
    private final ReservationDate reservationDate;
    private final ReservationDuration duration;

    public TableReservedEvent(Object source, TableId tableId, CustomerId customerId, ReservationDate reservationDate, ReservationDuration duration, UuidGenerator uuidGenerator) {
        super(source, uuidGenerator);
        this.tableId = tableId;
        this.customerId = customerId;
        this.reservationDate = reservationDate;
        this.duration = duration;
    }
}