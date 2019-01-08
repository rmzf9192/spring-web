package com.el.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/8 10:30
 * @Version:V1.0
 * @Description:RootConfig
 */
//Spring的容器，不扫描Controller
@ComponentScan(value = "com.el.web",excludeFilters = {
        @Filter(type=FilterType.ANNOTATION, classes={Controller.class})
})
public class RootConfig {
}
