package com.el.spring.annotation;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
@Data
public class UserAction {
	@Autowired
	private UsreService usreService;


	public void execute(){
		System.out.println("接受请求");
		usreService.addNew();
	}

}
