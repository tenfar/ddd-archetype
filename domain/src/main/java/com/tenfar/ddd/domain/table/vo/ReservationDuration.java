package com.tenfar.ddd.domain.table.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = false)
public final class ReservationDuration {
    private final Duration duration;

}
