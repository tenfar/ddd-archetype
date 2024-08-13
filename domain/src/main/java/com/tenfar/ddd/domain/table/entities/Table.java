package com.tenfar.ddd.domain.table.entities;

import com.tenfar.ddd.common.enums.TableStatus;
import com.tenfar.ddd.common.utils.UuidGenerator;
import com.tenfar.ddd.domain.table.events.TableReservedEvent;
import com.tenfar.ddd.domain.table.vo.CustomerId;
import com.tenfar.ddd.domain.table.vo.ReservationDate;
import com.tenfar.ddd.domain.table.vo.ReservationDuration;
import com.tenfar.ddd.domain.table.vo.TableId;
import lombok.Data;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Document(collection = "tables")
public class Table {
    @Id
    private final TableId id;
    private final int capacity;
    private final ApplicationEventPublisher eventPublisher;
    private final UuidGenerator uuidGenerator;
    private List<Reservation> reservations;
    private TableStatus status;

    public Table(TableId id, int capacity, ApplicationEventPublisher eventPublisher, UuidGenerator uuidGenerator) {
        this.id = id;
        this.capacity = capacity;
        this.status = TableStatus.AVAILABLE;
        this.reservations = new ArrayList<>();
        this.eventPublisher = eventPublisher;
        this.uuidGenerator = uuidGenerator;
    }

    public void reserve(CustomerId customerId, ReservationDate reservationDate, ReservationDuration duration) {
        if (status == TableStatus.AVAILABLE) {
            reservations.add(new Reservation(customerId, reservationDate, duration));
            status = TableStatus.RESERVED;
            eventPublisher.publishEvent(new TableReservedEvent(this, this.id, customerId, reservationDate, duration, uuidGenerator));
        } else {
            throw new IllegalStateException("Table is not available for reservation.");
        }
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}