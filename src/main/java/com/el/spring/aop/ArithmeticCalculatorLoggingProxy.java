package com.el.spring.aop;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/31 15:10
 * @Version:V1.0
 * @Description:ArithmeticCalculatorLoggingProxy
 */
public class ArithmeticCalculatorLoggingProxy {
    //要代理的对象
    private ArithmeticCalculator target;

    public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
        super();
        this.target = target;
    }

    //返回代理对象
    public ArithmeticCalculator getCalculator(){
        ArithmeticCalculator proxy=null;

        ClassLoader classLoader = target.getClass().getClassLoader();

        Class[] classes = {ArithmeticCalculator.class};
        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             *
             * @param proxy：代理对象，一般不使用该对象
             * @param method：正在被调用的方法
             * @param args：调用方法的传参
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                //打印日志
                System.out.println("[before] The method "+methodName+" begins with "+ Arrays.asList(args));

                //调用目标方法
                Object result=null;


                try {
                    //前置通知
                    result=  method.invoke(target,args);
                    //返回通知，可以访问到方法的返回值。
                } catch (Exception e) {
                    e.printStackTrace();
                    //异常通知，可以访问到方法出现的异常
                }

                //后置通知，因为该方法可能出现异常，所以访问不到方法的返回值。

                //打印日志
                System.out.println("[after] The method ends with "+result);

                return result;
            }
        };

        /**
         * classLoader：代理对象的类加载器
         * classes：指定代理对象的类型. 即代理代理对象中可以有哪些方法.
         * invocationHandler:当具体调用代理对象的方法时, 应该如何进行响应, 实际上就是调用 InvocationHandler 的 invoke 方法
         */
       proxy = (ArithmeticCalculator) Proxy.newProxyInstance(classLoader, classes, invocationHandler);

       return proxy;
    }
}
