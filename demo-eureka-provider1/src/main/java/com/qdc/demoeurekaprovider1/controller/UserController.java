package com.qdc.demoeurekaprovider1.controller;

import com.qdc.demoeurekaprovider1.pojo.User;
import com.qdc.demoeurekaprovider1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${spring.cloud.client.ip-address}")
    String ipaddr;
    @Value("${server.port}")
    int port;
    //addUser方法接受前端传过来的数据也是josn格式
    //@RequestBody把json转换为user对象
    @RequestMapping(value = "/add",method = RequestMethod.POST )
    public boolean addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public boolean delUser( @RequestParam(value="username",required = true) String name){
        return userService.delUser(name);
    }
    @RequestMapping(value = "/userall",method = RequestMethod.GET)
    public List<User> selectAllUsers(){
        List<User> users = userService.selectAllUsers();
        return users;
    }
    @RequestMapping(value = "/details",method = RequestMethod.GET)
    public User selectUserById(@RequestParam(value = "userid",required = true) String id){
        User users = userService.selectUserById(id);
        return users;
    }
    @RequestMapping(value = "/sayHi",method = RequestMethod.GET)
    public String Hello(@RequestParam(value = "sleep_seconds",required = true)int sleep_seconds) throws InterruptedException {
        System.out.println("休眠时间" + sleep_seconds);
        Thread.sleep(sleep_seconds*1000);
        return "Hello,我在"+ipaddr+":"+port;
    }



}
