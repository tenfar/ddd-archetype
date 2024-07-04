package com.tenfar.ddd.domain.table.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerId {
    private final String id;
}