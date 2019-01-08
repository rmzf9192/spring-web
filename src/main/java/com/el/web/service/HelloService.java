package com.el.web.service;

import org.springframework.stereotype.Service;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/8 11:19
 * @Version:V1.0
 * @Description:HelloService
 */
@Service
public class HelloService {
    public String sayHello(String name){
        return "hell"+name;
    }
}
