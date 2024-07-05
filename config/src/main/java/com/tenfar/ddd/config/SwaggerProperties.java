package com.tenfar.ddd.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private String basePackage;
    private String host;
    private Boolean enabled;
    private String title;
    private String version;
    private String description;

}