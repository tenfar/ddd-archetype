package com.tenfar.ddd.infrastructure.gateway;

import com.tenfar.ddd.infrastructure.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternalGateway {

    private final RestClientService restClientService;

    @Autowired
    public ExternalGateway(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    public <T> T callExternalService(String endpoint, Object request, Class<T> responseType) {
        try {
            if (request == null) {
                return restClientService.getForObject(endpoint, responseType);
            } else {
                return restClientService.postForObject(endpoint, request, responseType);
            }
        } catch (Exception e) {
            // Handle exception, e.g. log or throw custom exception
            throw new RuntimeException("Error calling external service", e);
        }
    }
}