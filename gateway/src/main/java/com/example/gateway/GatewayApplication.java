package com.example.gateway;

import com.netflix.discovery.EurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Lazy;


@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    @Lazy
    EurekaClient eurekaClient;
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
