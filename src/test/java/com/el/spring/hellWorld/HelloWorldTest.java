package com.el.spring.hellWorld;

import com.el.spring.ref.Service;
import com.el.spring.xml.helloworld.Car;
import com.el.spring.xml.helloworld.HelloWorld;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/20 11:26
 * @Version:V1.0
 * @Description:HelloWorldTest
 */
@Slf4j
public class HelloWorldTest {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");

    @Test
    public void test(){

        //通过属性注入值
        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");

        helloWorld.hello();
        System.out.println("不同方式注入值=====================");

        //通过构造器注入值:默认单例，在创建容器时赋值
        HelloWorld helloWorld2 = (HelloWorld) applicationContext.getBean("helloWorld2");
        helloWorld2.hello();


        Car car = (Car) applicationContext.getBean("car");

        System.out.println("car :{}"+car);

        Service service = (Service) applicationContext.getBean("service");
        service.save();
    }
}
