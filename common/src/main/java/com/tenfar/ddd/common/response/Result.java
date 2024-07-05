package com.tenfar.ddd.common.response;

import lombok.Data;
import java.util.List;

@Data
public class Result<T> {
    private T data;
    private Integer code;
    private Error error;
    private Meta meta;
}