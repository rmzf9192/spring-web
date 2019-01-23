package com.el.spring.xml.helloworld;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/20 11:14
 * @Version:V1.0
 * @Description:HelloWord
 */
public class HelloWorld {

    private String user;

    public HelloWorld(){
        System.out.println("HelloWord Constructor...... ");
    }

    public HelloWorld(String user) {
        System.out.println("有参的构造方法:"+user);
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        System.out.println("HelloWorld method setUser:"+user);
        this.user = user;
    }
    public void hello(){
        System.out.println("Hello: " + user);
    }
}
