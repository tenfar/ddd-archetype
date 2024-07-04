package com.tenfar.ddd.dto;

import lombok.*;

@Data
@NoArgsConstructor(force = true)
public class TableDTO {
    private String id;
    private String name;
    private int capacity;
}
