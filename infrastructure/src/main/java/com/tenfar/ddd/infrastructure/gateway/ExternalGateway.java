package com.tenfar.ddd.infrastructure.gateway;

import com.tenfar.ddd.infrastructure.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternalGateway {

    @Autowired
    private RestClientService restClientService;

    public <T> T callExternalService(String endpoint, Object request, Class<T> responseType) {
        if (request == null) {
            return restClientService.getForObject(endpoint, responseType);
        } else {
            return restClientService.postForObject(endpoint, request, responseType);
        }
    }
}