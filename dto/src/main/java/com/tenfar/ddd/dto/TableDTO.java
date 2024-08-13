package com.tenfar.ddd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
public class TableDTO {
    private String id;
    private String status;
    private int capacity;
}
