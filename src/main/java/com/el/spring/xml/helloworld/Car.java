package com.el.spring.xml.helloworld;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/20 11:14
 * @Version:V1.0
 * @Description:HelloWord
 */
@Slf4j
public class Car {

	private String company;
	private String brand;

	private int maxSpeed;
	private float price;

	public Car(String company, String brand, float price) {
		super();
		this.company = company;
		this.brand = brand;
		this.price = price;
		System.out.println("构造函数的参数是：String，String,float");
	}

	public Car(String company, String brand, int maxSpeed) {
		super();
		System.out.println("构造函数的参数是：String，String,int");
		this.company = company;
		this.brand = brand;
		this.maxSpeed = maxSpeed;
	}

	public Car(String company, String brand, int maxSpeed, float price) {
		super();
		this.company = company;
		this.brand = brand;
		this.maxSpeed = maxSpeed;
		this.price = price;
		System.out.println("构造函数的参数是：String，String,int,float");
	}

	@Override
	public String toString() {
		return "Car [company=" + company + ", brand=" + brand + ", maxSpeed="
				+ maxSpeed + ", price=" + price + "]";
	}
}
