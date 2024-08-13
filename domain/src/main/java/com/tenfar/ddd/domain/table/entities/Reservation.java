package com.tenfar.ddd.domain.table.entities;

import com.tenfar.ddd.domain.table.vo.CustomerId;
import com.tenfar.ddd.domain.table.vo.ReservationDate;
import com.tenfar.ddd.domain.table.vo.ReservationDuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private  CustomerId customerId;
    private  ReservationDate reservationDate;
    private  ReservationDuration duration;
}