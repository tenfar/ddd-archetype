package com.tenfar.ddd.application.events.handlers;

import com.tenfar.ddd.application.events.annotations.EventHandlerFor;
import com.tenfar.ddd.domain.table.events.TableCreatedEvent;
import org.springframework.stereotype.Component;

/**
 * TableCreatedEventHandler 類用於處理 TableCreatedEvent 事件。
 * 此類別通過 @EventHandlerFor 註解標記，並指定處理的事件類型和名稱。
 */
@Component
@EventHandlerFor(value = TableCreatedEvent.class, name = "TableCreatedEventHandler")
public class TableCreatedEventHandler implements EventHandler<TableCreatedEvent> {
    /**
     * 處理 TableCreatedEvent 事件的方法。
     * 此方法會輸出創建的表格信息。
     *
     * @param event 要處理的 TableCreatedEvent 事件
     */
    @Override
    public void handle(TableCreatedEvent event) {
        // 處理 TableCreatedEvent 的邏輯
        System.out.println("Table created: " + event.getTable());
        // 樣例邏輯：將創建的表格信息保存到數據庫
        // saveTableToDatabase(event.getTable());
    }

    /**
     * 樣例方法：將表格信息保存到數據庫。
     * 此方法僅為示例，具體實現需根據實際需求進行。
     *
     * @param table 要保存的表格信息
     */
    private void saveTableToDatabase(Object table) {
        // 實際的保存邏輯
        System.out.println("Saving table to database: " + table);
    }
}