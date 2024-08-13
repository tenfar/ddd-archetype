package com.tenfar.ddd.infrastructure.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final RestTemplateProperties restTemplateProperties;

    public RestTemplateConfig(RestTemplateProperties restTemplateProperties) {
        this.restTemplateProperties = restTemplateProperties;
    }

    @Bean
    public RestTemplate restTemplate() {
        // 創建連接管理器，設置最大連接數和每個路由的最大連接數
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(restTemplateProperties.getMaxTotalConnections());
        connectionManager.setDefaultMaxPerRoute(restTemplateProperties.getDefaultMaxPerRoute());
        // 創建HTTP客戶端，使用自定義的連接管理器
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        // 創建請求工廠，設置HTTP客戶端和超時時間
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        requestFactory.setConnectTimeout(restTemplateProperties.getConnectTimeout());
        requestFactory.setConnectionRequestTimeout(restTemplateProperties.getReadTimeout());

        // 創建RestTemplate實例，使用自定義的請求工廠
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        // 如果配置不緩存請求體，則添加攔截器以關閉連接
        if (!restTemplateProperties.getBufferRequestBody()) {
            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().set("Connection", "close");
                return execution.execute(request, body);
            });
        }

        return restTemplate;
    }
}