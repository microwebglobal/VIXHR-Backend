package com.microwebglobal.vixhr.auth;

import com.microwebglobal.vixhr.common.GlobalExceptionHandler;
import com.microwebglobal.vixhr.common.config.JpaConfig;
import com.microwebglobal.vixhr.common.config.OpenApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import({GlobalExceptionHandler.class, OpenApiConfig.class, JpaConfig.class})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}