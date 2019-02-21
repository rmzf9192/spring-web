package com.el.spring.xml.helloworld;

import java.util.List;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/20 11:14
 * @Version:V1.0
 * @Description:HelloWord
 */
public class User {

	private String userName;
	private List<Car> cars;
	
	private String wifeName;
	
	public String getWifeName() {
		return wifeName;
	}

	public void setWifeName(String wifeName) {
		System.out.println("setWifhName: " + wifeName);
		this.wifeName = wifeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	public User() {
		System.out.println("User's Construtor...");
	}


	@Override
	public String toString() {
		return "User{" +
				"userName='" + userName + '\'' +
				", cars=" + cars +
				", wifeName='" + wifeName + '\'' +
				'}';
	}

	public void init(){
		System.out.println("init method...");
	}
	
	public void destroy(){
		System.out.println("destroy method...");
	}

}
