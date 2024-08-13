package com.tenfar.ddd.infrastructure.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * RestClientService 是一个使用 RestTemplate 进行 HTTP 请求的服务类。
 * 使用方法：
 * 1. 通过构造方法注入 RestTemplate 实例。
 * 2. 调用 getForObject 方法进行 GET 请求。
 * 3. 调用 postForObject 方法进行 POST 请求。
 * 示例：
 * - GET 请求：restClientService.getForObject("http://example.com", String.class);
 * - POST 请求：restClientService.postForObject("http://example.com", requestObject, String.class);
 */
@Service
public class RestClientService {

    private final RestTemplate restTemplate;

    /**
     * 构造方法注入 RestTemplate 实例。
     *
     * @param restTemplate RestTemplate 实例
     */
    public RestClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 发送 GET 请求并返回响应对象。
     *
     * @param url          请求 URL
     * @param responseType 响应类型
     * @param <T>          响应类型泛型
     * @return 响应对象
     */
    public <T> T getForObject(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    /**
     * 发送 POST 请求并返回响应对象。
     *
     * @param url          请求 URL
     * @param request      请求体对象
     * @param responseType 响应类型
     * @param <T>          响应类型泛型
     * @return 响应对象
     */
    public <T> T postForObject(String url, Object request, Class<T> responseType) {
        return restTemplate.postForObject(url, request, responseType);
    }
}
