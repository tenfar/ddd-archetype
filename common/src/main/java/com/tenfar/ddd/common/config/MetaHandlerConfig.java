package com.tenfar.ddd.common.config;
import com.tenfar.ddd.common.handler.MetaHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.tenfar.ddd.common.handler") // Adjust the package as necessary
public class MetaHandlerConfig {

    private final List<MetaHandler> metaHandlers; // Automatically inject all MetaHandler beans

    public MetaHandlerConfig(List<MetaHandler> metaHandlers) {
        this.metaHandlers = metaHandlers;
    }

    @Bean
    @Scope("singleton")
    public Map<Class<?>, MetaHandler> metaHandlerMap() {
        Map<Class<?>, MetaHandler> handlerMap = new HashMap<>();
        for (MetaHandler handler : metaHandlers) {
            // Register the handler based on the type it handles
            handlerMap.put(handler.getHandledType(), handler);
        }
        return handlerMap;
    }
}