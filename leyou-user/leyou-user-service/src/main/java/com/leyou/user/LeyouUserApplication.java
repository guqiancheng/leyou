package com.leyou.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Guqiancheng
 * @date: 2020-05-27 0027 12:04
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.user.mapper")
public class LeyouUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeyouUserApplication.class, args);
    }
}
