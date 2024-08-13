package com.tenfar.ddd.launcher;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.context-path");
        if (path == null) {
            path = "";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("Failed to get host address", e);
            return;
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application DDD is running! Access URLs:\n\t" +
                "Local: \t\thttp://" + hostAddress + ":" + port + path + "/\n\t" +
                "External: \thttp://" + hostAddress + ":" + port + path + "/\n\t" +
                "Swagger-UI: \t\thttp://" + hostAddress + ":" + port + path + "/swagger-ui.html\n\t" +
                "----------------------------------------------------------");
    }
}