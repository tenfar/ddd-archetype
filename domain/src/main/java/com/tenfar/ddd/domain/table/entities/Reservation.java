
package com.tenfar.ddd.domain.table.entities;

import com.tenfar.ddd.domain.table.vo.CustomerId;
import com.tenfar.ddd.domain.table.vo.ReservationDate;
import com.tenfar.ddd.domain.table.vo.ReservationDuration;
import lombok.Data;


@Data
public class Reservation {
    private final CustomerId customerId;
    private final ReservationDate reservationDate;
    private final ReservationDuration duration;
}