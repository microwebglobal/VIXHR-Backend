package com.microwebglobal.vixhr.company;

import com.microwebglobal.vixhr.common.GlobalExceptionHandler;
import com.microwebglobal.vixhr.common.config.JpaConfig;
import com.microwebglobal.vixhr.common.config.OpenApiConfig;
import com.microwebglobal.vixhr.common.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient

@SpringBootApplication
@Import({GlobalExceptionHandler.class, SecurityConfig.class, OpenApiConfig.class, JpaConfig.class})
public class CompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}
}
