package com.tenfar.ddd.infrastructure.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    private final MongoProperties mongoProperties;

    @Autowired
    public MongoConfig(MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
    }

    @Bean
    public MongoClient primaryMongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoProperties.getPrimaryUri()))
                .applyToConnectionPoolSettings(builder -> builder.maxSize(mongoProperties.getMaxConnections()))
                .applyToSocketSettings(builder -> {
                    builder.connectTimeout(mongoProperties.getConnectTimeoutMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                    builder.readTimeout(mongoProperties.getSocketTimeoutMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                })
                .applyToConnectionPoolSettings(builder -> {
                    builder.maxWaitTime(mongoProperties.getMaxWaitTimeMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                    builder.maintenanceFrequency(mongoProperties.getMaintenanceIntervalMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                    builder.minSize(mongoProperties.getMinConnections());
                    builder.maintenanceInitialDelay(mongoProperties.getConnectionCheckIntervalMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                })
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(primaryMongoClient(), mongoProperties.getPrimaryDatabase());
    }

    @Bean
    public MongoClient secondaryMongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoProperties.getSecondaryUri()))
                .applyToConnectionPoolSettings(builder -> builder.maxSize(mongoProperties.getMaxConnections()))
                .applyToSocketSettings(builder -> {
                    builder.connectTimeout(mongoProperties.getConnectTimeoutMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                    builder.readTimeout(mongoProperties.getSocketTimeoutMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                })
                .applyToConnectionPoolSettings(builder -> {
                    builder.maxWaitTime(mongoProperties.getMaxWaitTimeMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                    builder.maintenanceFrequency(mongoProperties.getMaintenanceIntervalMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                    builder.minSize(mongoProperties.getMinConnections());
                    builder.maintenanceInitialDelay(mongoProperties.getConnectionCheckIntervalMs(), java.util.concurrent.TimeUnit.MILLISECONDS);
                })
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(secondaryMongoClient(), mongoProperties.getSecondaryDatabase());
    }
}