package com.el.spring.hellWorld;

import com.el.spring.annotation.UserAction;
import com.el.spring.aop.ArithmeticCalculator;
import com.el.spring.aop.ArithmeticCalculatorImpl;
import com.el.spring.aop.ArithmeticCalculatorLoggingProxy;
import com.el.spring.ref.Action;
import com.el.spring.ref.Dao;
import com.el.spring.ref.Service;
import com.el.spring.xml.helloworld.Car;
import com.el.spring.xml.helloworld.HelloWorld;
import com.el.spring.xml.helloworld.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/20 11:26
 * @Version:V1.0
 * @Description:HelloWorldTest
 */
@Slf4j
public class HelloWorldTest {



    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //通过属性注入值
        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");

        helloWorld.hello();
        System.out.println("不同方式注入值=====================");

        //通过构造器注入值:默认单例，在创建容器时赋值
        HelloWorld helloWorld2 = (HelloWorld) applicationContext.getBean("helloWorld2");
        helloWorld2.hello();


        Car car = (Car) applicationContext.getBean("car");

        System.out.println("car :{}"+car);

        //测试ref
        Service service = (Service) applicationContext.getBean("service");
        service.save();
        Service service1 = (Service) applicationContext.getBean("service1");

        service1.save();

        Action action = (Action) applicationContext.getBean("action");
        action.execute();

        Dao dao2 = (Dao) applicationContext.getBean("dao2");
        dao2.save();

        User user = (User) applicationContext.getBean("user");
        System.out.println(user);


        User user2 = (User) applicationContext.getBean("user2");
        System.out.println("引用外部集合："+user2);

        User user3 = (User) applicationContext.getBean("user3");
        System.out.println("P标签的使用："+user3);

        User user4 = (User) applicationContext.getBean("user4");
        System.out.println("parent标签的使用："+user4);

        User user5 = (User) applicationContext.getBean("user5");
        System.out.println("parent标签的使用："+user5);
    }

    @Test
    public void test2() throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean_auto.xml");
        Dao dao = (Dao) applicationContext.getBean("dao");
       dao.save();
        Action action = applicationContext.getBean(Action.class);

        action.execute();

        //测试 bean 的作用域
        Dao dao1 = (Dao) applicationContext.getBean("dao2");
        Dao dao2 = (Dao) applicationContext.getBean("dao2");

        System.out.println(dao1 == dao2);
/*
        //测试使用外部属性文件
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource.getConnection());*/

        //测试 spEL
        User boy = (User) applicationContext.getBean("boy");
        System.out.println(boy.getUserName() + ":" + boy.getWifeName());


        DateFormat dateFormat = (DateFormat) applicationContext.getBean("dateFormat");

        System.out.println(dateFormat.format(new Date()));

        Date date = (Date) applicationContext.getBean("datetime");
        System.out.println(date);

        User user = (User) applicationContext.getBean("user");
        System.out.println(user);

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    @Test
    public void test3(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");

        UserAction userAction = ctx.getBean(UserAction.class);
        userAction.execute();
    }

    @Test
    public void test4(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld hellWorld = (HelloWorld) ctx.getBean("hellWorld");
        hellWorld.hello();
        ((ClassPathXmlApplicationContext) ctx).close();
    }

    @Test
    public void test5(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserAction userAction = (UserAction) ctx.getBean("userAction");

        userAction.execute();
    }

    @Test
    public void test6(){
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();

		arithmeticCalculator =
				new ArithmeticCalculatorLoggingProxy(arithmeticCalculator).getCalculator();

		int result = arithmeticCalculator.add(11, 12);
		System.out.println("result:" + result);

		result = arithmeticCalculator.div(21, 3);
		System.out.println("result:" + result);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
        ArithmeticCalculator calculatorBean = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");

        System.out.println(calculatorBean.getClass().getName());

        int result1 = calculatorBean.add(11, 12);
        System.out.println("result:" + result1);

        result1 = arithmeticCalculator.div(21, 3);
        System.out.println("result:" + result1);
    }
}
