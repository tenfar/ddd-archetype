package com.tenfar.ddd.domain.table.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDuration {
    private Duration duration;
}
