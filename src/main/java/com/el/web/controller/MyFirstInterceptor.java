package com.el.web.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/8 11:15
 * @Version:V1.0
 * @Description:MyFirstInterceptor
 */
public class MyFirstInterceptor implements HandlerInterceptor {
    //目标方法运行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("preHandle..."+request.getRequestURI());
        return true;
    }

    //目标方法执行正确以后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");

    }

    //页面响应以后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("afterCompletion...");
    }
}
