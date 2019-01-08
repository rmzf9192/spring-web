package com.el.web.config;

import com.el.web.controller.MyFirstInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/8 11:06
 * @Version:V1.0
 * @Description:AppConfig
 */
//Spring mvc只扫描Controller，子容器
//useDefaultFilters=false 禁用默认的过滤规则；
@ComponentScan(value = "com.el.web",includeFilters = {
        @Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
    //定制
    //定制视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //默认所有的页面都从 /WEB-INF/ xxx .jsp
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    //静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();//开启静态资源
    }

    //定制拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
    }
}
