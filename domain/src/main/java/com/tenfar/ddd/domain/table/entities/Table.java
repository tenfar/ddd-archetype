package com.tenfar.ddd.domain.table.entities;

import com.tenfar.ddd.domain.table.events.TableReservedEvent;
import com.tenfar.ddd.domain.table.vo.CustomerId;
import com.tenfar.ddd.domain.table.vo.ReservationDate;
import com.tenfar.ddd.domain.table.vo.ReservationDuration;
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
    private final String id;
    private final int capacity;
    private List<Reservation> reservations;
    private final EventB eventPublisher;
    private String status;

    public Table(String id, int capacity, ApplicationEventPublisher eventPublisher) {
        this.id = id;
        this.capacity = capacity;
        this.status = "available";
        this.reservations = new ArrayList<>();
        this.eventPublisher = eventPublisher;
    }

    public void reserve(CustomerId customerId, ReservationDate reservationDate, ReservationDuration duration) {
        if ("available".equals(status)) {
            reservations.add(new Reservation(customerId, reservationDate, duration));
            status = "reserved";
            eventPublisher.publishEvent(new TableReservedEvent(this, this.id, customerId, reservationDate, duration));
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

