package com.el.web;

import com.el.web.config.AppConfig;
import com.el.web.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/8 10:47
 * @Version:V1.0
 * @Description:MyWebAppInitializer
 */
//web容器启动的时候，创建对象。调用方法来初始化以前的前端控制器
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //获取根容器的配置类：Spring配置文件，容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    //获取web容器的配置类：SpringMvc的配置文件，子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    //获取DispatcherServlet的映射信息
    //  /:拦截所有请求（包括静态资源，xx.js,xxx.png）,但不包括xxx.jsp
    //  /*:拦截所有请求，jsp页面都拦截，jsp页面是tomcat引擎解析的
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
