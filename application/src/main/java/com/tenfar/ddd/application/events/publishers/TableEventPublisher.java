package com.tenfar.ddd.application.events.publishers;

import com.tenfar.ddd.domain.table.events.TableCreatedEvent;
import com.tenfar.ddd.domain.table.events.TableDeletedEvent;
import com.tenfar.ddd.domain.table.events.TableUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * TableEventPublisher 類用於發布與 Table 相關的領域事件。
 * 這個類別通過 Spring 的 ApplicationEventPublisher 來發布事件。
 */
@Component
public class TableEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 構造函數，注入 ApplicationEventPublisher 實例。
     *
     * @param eventPublisher Spring 的 ApplicationEventPublisher 實例
     */
    public TableEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * 發布 TableCreatedEvent 事件。
     *
     * @param event 要發布的 TableCreatedEvent 實例
     */
    public void publishTableCreatedEvent(TableCreatedEvent event) {
        eventPublisher.publishEvent(event);
    }

    /**
     * 發布 TableUpdatedEvent 事件。
     *
     * @param event 要發布的 TableUpdatedEvent 實例
     */
    public void publishTableUpdatedEvent(TableUpdatedEvent event) {
        eventPublisher.publishEvent(event);
    }

    /**
     * 發布 TableDeletedEvent 事件。
     *
     * @param event 要發布的 TableDeletedEvent 實例
     */
    public void publishTableDeletedEvent(TableDeletedEvent event) {
        eventPublisher.publishEvent(event);
    }
}