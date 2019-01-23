package com.el.spring.ref;
/**
 * @Auther: roman.zhang
 * @Date: 2019/1/21 10:14
 * @Version:V1.0
 * @Description:HelloWord
 */
public class Dao {

	private String dataSource = "dbcp";
	
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	public void save(){
		System.out.println("save by " + dataSource);
	}
	
	public Dao() {
		System.out.println("Dao's Constructor...");
	}
	
}
