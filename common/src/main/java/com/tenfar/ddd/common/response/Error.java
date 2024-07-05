package com.tenfar.ddd.common.response;

import lombok.Data;

@Data
public class Error {
    private Integer code;
    private String message;
}


