package com.tenfar.ddd.domain.event;

import com.tenfar.ddd.common.utils.UuidGenerator;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;

/**
 * DomainEvent抽象類，繼承自Spring的ApplicationEvent
 * 用於表示領域事件，包含事件ID和發生時間
 */
@Getter
@ToString
public abstract class DomainEvent extends ApplicationEvent {

    private final Long eventId;
    private final Instant occurredOn;
    private final UuidGenerator uuidGenerator;

    /**
     * 構造函數，初始化事件ID和發生時間
     *
     * @param source        事件來源對象
     * @param uuidGenerator UuidGenerator實例
     */
    public DomainEvent(Object source, UuidGenerator uuidGenerator) {
        super(source);
        this.uuidGenerator = uuidGenerator;
        this.eventId = uuidGenerator.generateUUID(); // 使用UuidGenerator生成UUID並轉換為Long
        this.occurredOn = Instant.now();
    }
}
