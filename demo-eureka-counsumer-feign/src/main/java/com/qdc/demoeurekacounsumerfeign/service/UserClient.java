package com.qdc.demoeurekacounsumerfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
//使用注解设置服务提供者名

@FeignClient(name = "eureka-provider1")
public interface UserClient {
    @RequestMapping(value = "/port")
    public String hello();
}
