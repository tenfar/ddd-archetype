package com.tenfar.ddd.common.enums;

import lombok.Getter;

@Getter
public enum TableStatus implements BaseEnum<Integer> {
    AVAILABLE(0, "available"),
    RESERVED(1, "reserved");

    private final Integer code;
    private final String message;

    TableStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}