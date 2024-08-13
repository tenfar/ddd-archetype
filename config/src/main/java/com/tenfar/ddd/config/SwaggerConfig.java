package com.tenfar.ddd.config;

import com.google.common.base.Predicate;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String SPLITOR = ";";
    @Resource
    private SwaggerProperties swaggerProperties;

    private static Optional<String> declaringClassPackage(RequestHandler input) {
        return input.getPatternsCondition()
                .getPatterns()
                .stream()
                .map(pattern -> pattern.replace("/", ".")) /* Convert slashes to dots */
                .filter(java.util.Objects::nonNull) /* Correct null check */
                .findFirst();
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> {
            Optional<String> declaringClassPackage = declaringClassPackage(input);
            return declaringClassPackage.map(pkg -> {
                for (String strPackage : basePackage.split(SPLITOR)) {
                    if (pkg.startsWith(strPackage)) {
                        return true;
                    }
                }
                return false;
            }).orElse(false);
        };
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerProperties.getEnabled())
                .host(swaggerProperties.getHost())
                .select()
                .apis(basePackage(swaggerProperties.getBasePackage())) // 指定接口层的包
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .protocols(swaggerProperties.getProtocols() != null ? new HashSet<>(swaggerProperties.getProtocols()) : Collections.emptySet()); // 使用swaggerProperties中的protocols
    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion());

        if (swaggerProperties.getContact() != null) {
            apiInfoBuilder.contact(new springfox.documentation.service.Contact(
                    swaggerProperties.getContact().getName(),
                    swaggerProperties.getContact().getUrl(),
                    swaggerProperties.getContact().getEmail()
            ));
        }

        return apiInfoBuilder.build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }
}