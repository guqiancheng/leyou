package com.leyou.auth;

import com.leyou.auth.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: Guqiancheng
 * @date: 2020-05-28 0028 21:32
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties({JwtProperties.class})
public class LeyouAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeyouAuthApplication.class, args);
    }
}
