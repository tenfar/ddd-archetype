package com.tenfar.ddd.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Swagger配置属性类
 * 用于从配置文件中读取Swagger相关的配置信息
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    /**
     * 基础包路径
     * 用于指定Swagger扫描的包路径
     */
    private String basePackage;

    /**
     * 主机地址
     * 用于指定Swagger文档的主机地址
     */
    private String host;

    /**
     * 是否启用Swagger
     * 用于控制Swagger文档的启用状态
     */
    private Boolean enabled;

    /**
     * 文档标题
     * 用于指定Swagger文档的标题
     */
    private String title;

    /**
     * 文档版本
     * 用于指定Swagger文档的版本
     */
    private String version;

    /**
     * 文档描述
     * 用于指定Swagger文档的描述信息
     */
    private String description;

    /**
     * 协议列表
     * 用于指定Swagger文档支持的协议
     */
    private List<String> protocols; // 新增协议配置

    /**
     * 联系信息
     * 用于指定Swagger文档的联系信息
     */
    private Contact contact; // 新增联系信息配置

    /**
     * 联系信息类
     * 用于封装Swagger文档的联系信息
     */
    @Setter
    @Getter
    public static class Contact {
        /**
         * 联系人姓名
         */
        private String name;

        /**
         * 联系人URL
         */
        private String url;

        /**
         * 联系人邮箱
         */
        private String email;
    }
}