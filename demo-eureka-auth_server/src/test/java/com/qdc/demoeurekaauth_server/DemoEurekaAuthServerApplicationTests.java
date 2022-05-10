package com.qdc.demoeurekaauth_server;

import com.alibaba.druid.pool.DruidDataSource;
import com.qdc.demoeurekaauth_server.mapper.UserMapper;
import com.qdc.demoeurekaauth_server.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoEurekaAuthServerApplicationTests {
@Autowired
private DruidDataSource druidDataSource;
@Autowired
private UserMapper userMapper;
@Autowired
UserDetailsServiceImpl userDetailsService;
    @Test
    void contextLoads() {
        System.out.println(druidDataSource);
    }
    @Test
    void getUserTest(){
        System.out.println(userMapper.findByUsername("admin"));
    }
    @Test
    void getUserTest1(){
        System.out.println(userDetailsService.loadUserByUsername("admin"));
    }

}
