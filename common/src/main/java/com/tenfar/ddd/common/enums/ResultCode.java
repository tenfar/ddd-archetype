package com.tenfar.ddd.common.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200),
    FAIL(500),
    NOT_FOUND(404),
    BAD_REQUEST(400);

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }
}