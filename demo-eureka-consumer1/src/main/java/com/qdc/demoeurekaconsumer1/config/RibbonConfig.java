package com.qdc.demoeurekaconsumer1.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//这是一个配置类
@Configuration
public class RibbonConfig {
    @Bean
    @LoadBalanced
    //由这个对象调用服务者数据
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
