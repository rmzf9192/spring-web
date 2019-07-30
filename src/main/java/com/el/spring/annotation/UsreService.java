package com.el.spring.annotation;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@Data
public class UsreService {
	@Autowired
	private UserDao userDao;


	public void addNew(){
		System.out.println("添加新用户");
		userDao.save();
	}

}
