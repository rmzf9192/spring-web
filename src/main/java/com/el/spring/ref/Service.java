package com.el.spring.ref;
/**
 * @Auther: roman.zhang
 * @Date: 2019/1/20 11:34
 * @Version:V1.0
 * @Description:HelloWord
 */
public class Service {

	private Dao dao;
	
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public Dao getDao() {
		return dao;
	}
	
	public void save(){
		System.out.println("Service's save");
		dao.save();
	}
	
}
