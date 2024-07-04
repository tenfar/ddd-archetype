package com.tenfar.ddd.domain.table.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReservationDate {
    private final LocalDate date;
}
