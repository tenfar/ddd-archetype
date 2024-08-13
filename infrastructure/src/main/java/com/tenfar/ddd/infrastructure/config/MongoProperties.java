package com.tenfar.ddd.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mongodb")
@Getter
@Setter
public class MongoProperties {

    private String primaryUri = "mongodb://localhost:27017";
    private String primaryDatabase = "mongoDatabase";
    private String secondaryUri = "mongodb://localhost:27018";
    private String secondaryDatabase = "mongoDatabase2";

    /**
     * 連接池最大連接數
     * 用法: 配置MongoDB連接池的最大連接數，例如 100
     */
    private Integer maxConnections = 100;

    /**
     * 連接超時時間（毫秒）
     * 用法: 配置MongoDB連接超時時間，例如 10000
     */
    private Integer connectTimeoutMs = 10000;

    /**
     * 套接字超時時間（毫秒）
     * 用法: 配置MongoDB套接字超時時間，例如 30000
     */
    private Integer socketTimeoutMs = 30000;

    /**
     * 連接池最大等待時間（毫秒）
     * 用法: 配置MongoDB連接池最大等待時間，例如 20000
     */
    private Integer maxWaitTimeMs = 20000;

    /**
     * 連接池維護線程間隔時間（毫秒）
     * 用法: 配置MongoDB連接池維護線程間隔時間，例如 60000
     */
    private Integer maintenanceIntervalMs = 60000;

    /**
     * 連接池最小空閒連接數
     * 用法: 配置MongoDB連接池最小空閒連接數，例如 10
     */
    private Integer minConnections = 10;

    /**
     * 連接池連接超時檢查間隔時間（毫秒）
     * 用法: 配置MongoDB連接池連接超時檢查間隔時間，例如 60000
     */
    private Integer connectionCheckIntervalMs = 60000;

    /**
     * 連接池連接超時時間（毫秒）
     * 用法: 配置MongoDB連接池連接超時時間，例如 300000
     */
    private Integer connectionTimeoutMs = 300000;
}