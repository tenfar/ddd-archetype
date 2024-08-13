package com.tenfar.ddd.common.enums;

/**
 * BaseEnum接口，提供通用的枚举方法
 */
public interface BaseEnum<T> {
    T getCode();

    String getMessage();
}

