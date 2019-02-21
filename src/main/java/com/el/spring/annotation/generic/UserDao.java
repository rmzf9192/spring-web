package com.el.spring.annotation.generic;

import org.springframework.stereotype.Repository;

@Repository("userDao1")
public class UserDao extends BaseDao<User>{

}
