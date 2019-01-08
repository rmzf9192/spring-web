package com.el.web.controller;

import com.el.web.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/8 11:20
 * @Version:V1.0
 * @Description:HelloController
 */
@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        String hello = helloService.sayHello("tomcat。。。");
        return hello;
    }

    //访问静态资源
    @RequestMapping("/success")
    public String suc(){
        System.out.println("执行了");
        return "success";
    }
}
