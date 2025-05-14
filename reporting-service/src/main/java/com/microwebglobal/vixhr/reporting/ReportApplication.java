package com.microwebglobal.vixhr.reporting;

import com.microwebglobal.vixhr.common.GlobalExceptionHandler;
import com.microwebglobal.vixhr.common.config.JpaConfig;
import com.microwebglobal.vixhr.common.config.OpenApiConfig;
import com.microwebglobal.vixhr.common.config.RestClientConfig;
import com.microwebglobal.vixhr.common.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient

@SpringBootApplication
@Import({
        JpaConfig.class,
        OpenApiConfig.class,
        SecurityConfig.class,
        GlobalExceptionHandler.class,
        RestClientConfig.class
})
public class ReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }
}