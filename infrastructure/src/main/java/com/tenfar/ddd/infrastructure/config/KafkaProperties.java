package com.tenfar.ddd.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    /**
     * Kafka 集群的初始連接地址
     * 用法: 配置 Kafka 集群的初始連接地址，例如 "localhost:9092,localhost:9093"
     */
    private String bootstrapServers = "localhost:9092";

    /**
     * Kafka 消費者組 ID
     * 用法: 配置消費者所屬的消費者組 ID，例如 "my-group"
     */
    private String groupId = "default-group";

    /**
     * Kafka 主題名稱
     * 用法: 配置要消費或生產消息的主題名稱，例如 "my-topic"
     */
    private String topic = "default-topic";

    /**
     * 消費者偏移量重置策略
     * 用法: 配置消費者偏移量重置策略，例如 "earliest" 或 "latest"
     */
    private String autoOffsetReset = "earliest";

    /**
     * 會話超時時間（毫秒）
     * 用法: 配置消費者會話超時時間，例如 10000
     */
    private Integer sessionTimeoutMs = 10000;

    /**
     * 心跳間隔時間（毫秒）
     * 用法: 配置消費者心跳間隔時間，例如 3000
     */
    private Integer heartbeatIntervalMs = 3000;

    /**
     * 每次輪詢的最大記錄數
     * 用法: 配置消費者每次輪詢的最大記錄數，例如 500
     */
    private Integer maxPollRecords = 500;

    /**
     * 最大輪詢間隔時間（毫秒）
     * 用法: 配置消費者最大輪詢間隔時間，例如 300000
     */
    private Integer maxPollIntervalMs = 300000;

    /**
     * 請求超時時間（毫秒）
     * 用法: 配置消費者請求超時時間，例如 30000
     */
    private Integer requestTimeoutMs = 30000;

    /**
     * 重試間隔時間（毫秒）
     * 用法: 配置消費者重試間隔時間，例如 100
     */
    private Integer retryBackoffMs = 100;

    /**
     * 重新連接間隔時間（毫秒）
     * 用法: 配置消費者重新連接間隔時間，例如 50
     */
    private Integer reconnectBackoffMs = 50;

    /**
     * 最大重新連接間隔時間（毫秒）
     * 用法: 配置消費者最大重新連接間隔時間，例如 1000
     */
    private Integer reconnectBackoffMaxMs = 1000;

    /**
     * 最大阻塞時間（毫秒）
     * 用法: 配置消費者最大阻塞時間，例如 60000
     */
    private Integer maxBlockMs = 60000;

    /**
     * 接收緩衝區大小（字節）
     * 用法: 配置消費者接收緩衝區大小，例如 32768
     */
    private Integer receiveBufferBytes = 32768;

    /**
     * 發送緩衝區大小（字節）
     * 用法: 配置消費者發送緩衝區大小，例如 131072
     */
    private Integer sendBufferBytes = 131072;

    /**
     * 最大等待時間（毫秒）
     * 用法: 配置消費者最大等待時間，例如 500
     */
    private Integer fetchMaxWaitMs = 500;

    /**
     * 最小提取字節數
     * 用法: 配置消費者最小提取字節數，例如 1
     */
    private Integer fetchMinBytes = 1;

    /**
     * 最大提取字節數
     * 用法: 配置消費者最大提取字節數，例如 52428800
     */
    private Integer fetchMaxBytes = 52428800;

    /**
     * 是否檢查 CRC 校驗和
     * 用法: 配置是否檢查 CRC 校驗和，例如 true 或 false
     */
    private Boolean checkCrcs = true;

    /**
     * 客戶端 ID
     * 用法: 配置消費者客戶端 ID，例如 "my-client"
     */
    private String clientId = "";

    /**
     * 安全協議
     * 用法: 配置安全協議，例如 "PLAINTEXT" 或 "SSL"
     */
    private String securityProtocol = "PLAINTEXT";

    /**
     * SASL 機制
     * 用法: 配置 SASL 機制，例如 "GSSAPI" 或 "PLAIN"
     */
    private String saslMechanism = "GSSAPI";

    /**
     * SASL JAAS 配置
     * 用法: 配置 SASL JAAS 配置，例如 "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"user\" password=\"password\";"
     */
    private String saslJaasConfig = "";

    /**
     * SSL 密鑰密碼
     * 用法: 配置 SSL 密鑰密碼，例如 "my-key-password"
     */
    private String sslKeyPassword = "";

    /**
     * SSL 密鑰庫位置
     * 用法: 配置 SSL 密鑰庫位置，例如 "/path/to/keystore.jks"
     */
    private String sslKeystoreLocation = "";

    /**
     * SSL 密鑰庫密碼
     * 用法: 配置 SSL 密鑰庫密碼，例如 "my-keystore-password"
     */
    private String sslKeystorePassword = "";

    /**
     * SSL 信任庫位置
     * 用法: 配置 SSL 信任庫位置，例如 "/path/to/truststore.jks"
     */
    private String sslTruststoreLocation = "";

    /**
     * SSL 信任庫密碼
     * 用法: 配置 SSL 信任庫密碼，例如 "my-truststore-password"
     */
    private String sslTruststorePassword = "";

    /**
     * SSL 端點識別算法
     * 用法: 配置 SSL 端點識別算法，例如 "HTTPS"
     */
    private String sslEndpointIdentificationAlgorithm = "HTTPS";

    // 添加重試次數配置
    private Integer retries = 3;

    // 添加批處理大小配置
    private Integer batchSize = 16384;

    // 添加延遲時間配置
    private Integer lingerMs = 1;

    // 添加緩衝區內存配置
    private Integer bufferMemory = 33554432;

}