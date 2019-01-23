package com.el.spring.ref;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/21 11:15
 * @Version:V1.0
 * @Description:HelloWord
 */
public class Action {

	private Service service;
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public Service getService() {
		return service;
	}
	
	public void execute(){
		System.out.println("Action's execute...");
		service.save();
	}
	
}
