package com.tenfar.ddd.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RestTemplate的配置属性类
 */
@Component
@ConfigurationProperties(prefix = "rest.template")
@Getter
@Setter
public class RestTemplateProperties {

    /**
     * 连接超时时间，默认5秒
     */
    private Integer connectTimeout = 5000;

    /**
     * 读取超时时间，默认5秒
     */
    private Integer readTimeout = 5000;

    /**
     * 最大连接数，默认50
     */
    private Integer maxTotalConnections = 50;

    /**
     * 每个路由的最大连接数，默认20
     */
    private Integer defaultMaxPerRoute = 20;

    /**
     * 是否缓存请求体，默认true
     */
    private Boolean bufferRequestBody = true;

    /**
     * 请求失败是否抛出异常，默认true
     */
    private Boolean throwExceptionOnFailure = true;
}

