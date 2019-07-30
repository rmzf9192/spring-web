package com.el.spring.annotation;

import org.springframework.stereotype.Repository;

//@Repository
public class UserDao {

	public void save(){
		System.out.println("保存新用户");
	}

}
