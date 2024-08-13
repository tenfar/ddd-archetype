package com.tenfar.ddd.application.events.annotations;

import com.tenfar.ddd.domain.event.DomainEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此註解用於標記事件處理器類別，並指定其處理的事件類型和名稱。
 *
 * {@code @Target(ElementType.TYPE)} 表示此註解只能用於類別。
 * {@code @Retention(RetentionPolicy.RUNTIME)} 表示此註解在運行時可被反射機制讀取。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandlerFor {
    /**
     * 指定事件處理器所處理的事件類型。
     *
     * @return 事件類型
     */
    Class<? extends DomainEvent> value();

    /**
     * 指定事件處理器的名稱。
     *
     * @return 事件處理器名稱
     */
    String name();
}