package com.tenfar.ddd.infrastructure.utils;

import com.tenfar.ddd.common.utils.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * 基於雪花算法的UUID生成工具類
 * 該類用於生成全局唯一的ID，適用於分佈式系統。
 * 主要原理是結合時間戳、數據中心ID和工作節點ID來生成唯一ID。
 */
@Component
public class SnowIdUtils implements UuidGenerator {

    private final long twepoch = 1288834974657L; // 起始時間戳，用於減少生成的ID數值
    private final long workerIdBits = 5L; // 工作節點ID的位數
    private final long datacenterIdBits = 5L; // 數據中心ID的位數
    private final long sequenceBits = 12L; // 序列號的位數
    private final long workerIdShift = sequenceBits; // 工作節點ID的偏移量
    private final long datacenterIdShift = sequenceBits + workerIdBits; // 數據中心ID的偏移量
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; // 時間戳的偏移量
    private final long sequenceMask = ~(-1L << sequenceBits); // 用於限制序列號的範圍

    private long workerId; // 工作節點ID
    private long datacenterId; // 數據中心ID

    private long sequence = 0L; // 序列號
    private long lastTimestamp = -1L; // 上一次生成ID的時間戳

    @Autowired
    public SnowIdUtils(Environment env) {
        // 從環境變量中讀取工作節點ID和數據中心ID，如果未設置則默認為0
        this.workerId = Long.parseLong(env.getProperty("uuid.workerId", "0"));
        this.datacenterId = Long.parseLong(env.getProperty("uuid.datacenterId", "0"));
    }

    /**
     * 生成UUID
     * 該方法同步以確保在多線程環境下生成的ID唯一。
     *
     * @return 生成的UUID
     */
    public synchronized long generateId() {
        long timestamp = timeGen();

        // 檢查時鐘是否回撥，如果回撥則拋出異常
        if (timestamp < lastTimestamp) {
            // 處理時間回滾的情況
            timestamp = handleClockBackwards(timestamp);
        }

        // 如果同一時間戳內序列號遞增，如果達到最大值則等待下一毫秒
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        // 組合時間戳、數據中心ID、工作節點ID和序列號生成唯一ID
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    /**
     * 處理時間回滾的情況
     *
     * @param timestamp 當前時間戳
     * @return 處理後的時間戳
     */
    private long handleClockBackwards(long timestamp) {
        // 這裡可以根據具體需求實現不同的處理策略
        // 例如，等待時間追上lastTimestamp，或者直接拋出異常
        // 這裡選擇等待時間追上lastTimestamp
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 等待下一毫秒以獲取新的時間戳
     *
     * @param lastTimestamp 上一次生成ID的時間戳
     * @return 新的時間戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 獲取當前時間戳
     *
     * @return 當前時間戳
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 函數式編程範式，提供UUID生成器
     * 該方法返回一個Supplier，用於生成UUID。
     *
     * @return UUID生成器
     */
    public Supplier<Long> uuidSupplier() {
        return this::generateId;
    }

    // 實現UuidGenerator接口的generateUUID方法，返回生成的ID
    @Override
    public Long generateUUID() {
        return generateId();
    }
}
