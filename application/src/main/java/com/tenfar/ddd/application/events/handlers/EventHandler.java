package com.tenfar.ddd.application.events.handlers;

import com.tenfar.ddd.domain.event.DomainEvent;
import org.springframework.stereotype.Component;

/**
 * 事件處理器接口，定義了處理事件的方法。
 * 所有具體的事件處理器都應該實現此接口。
 *
 * @param <T> 事件類型，必須是 DomainEvent 的子類
 */
@Component
public interface EventHandler<T extends DomainEvent> {
    /**
     * 處理指定類型的事件。
     *
     * @param event 要處理的事件
     */
    void handle(T event);
}