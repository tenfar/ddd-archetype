package com.tenfar.ddd.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring MVC 配置
 * 该类用于配置Spring MVC的相关设置，包括资源处理器、跨域映射、消息转换器等
 */
@Configuration
@EnableWebMvc
@Slf4j
public class WebMvcSupport implements WebMvcConfigurer {

    /**
     * ObjectMapper instance for reuse
     */
    private final ObjectMapper objectMapper = createObjectMapper();

    /**
     * Create ObjectMapper with desired configuration
     */
    private ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    /**
     * Use the existing objectMapper instance
     */
    @Bean
    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    /**
     * 添加资源处理器
     * 用于处理Swagger UI的静态资源
     *
     * @param registry 资源处理器注册表
     */
    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 解决跨域问题
     * 配置跨域映射，允许所有来源、方法和头部信息，并支持凭证和最大缓存时间
     *
     * @param registry 跨域注册表
     */
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 配置消息转换器
     * 添加自定义的MappingJackson2HttpMessageConverter和ByteArrayHttpMessageConverter
     *
     * @param converters 消息转换器列表
     */
    @Override
    public void configureMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper());
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(converter);
        converters.add(byteArrayHttpMessageConverter());
    }

    /**
     * 创建ByteArrayHttpMessageConverter Bean
     * 配置支持的媒体类型
     *
     * @return 配置后的ByteArrayHttpMessageConverter实例
     */
    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
        return arrayHttpMessageConverter;
    }

    /**
     * 获取支持的媒体类型列表
     *
     * @return 支持的媒体类型列表
     */
    private List<MediaType> getSupportedMediaTypes() {
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.IMAGE_JPEG);
        list.add(MediaType.IMAGE_PNG);
        list.add(MediaType.APPLICATION_OCTET_STREAM);
        list.add(MediaType.APPLICATION_JSON);
        return list;
    }
}