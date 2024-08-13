package com.tenfar.ddd.common.utils;

/**
 * UuidGenerator接口
 * 提供生成UUID的方法
 */
public interface UuidGenerator {
    /**
     * 生成UUID
     *
     * @return 生成的UUID
     */
    Long generateUUID();
}
