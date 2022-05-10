package com.qdc.demoeurekaconsumer1;

import com.qdc.demoeurekaconsumer1.utils.HttpClientWithBasicAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import java.util.HashMap;
import java.util.Map;

//@EnableDiscoveryClient这个加不加都可以
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
//启动类上方加入注解@EnableHystrix开启熔断器
@EnableHystrix
public class DemoEurekaConsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaConsumer1Application.class, args);
        HttpClientWithBasicAuth auth=new HttpClientWithBasicAuth();
        String url="http://localhost:3333/oauth/token";
        Map<String,String> formData=new HashMap<>();
        formData.put("grant_type","client_credentials");
        formData.put("scope","all");
        String result=auth.send(url,"test","123456",formData);
        System.out.println(result);
    }

}
