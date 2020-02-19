package com.fruitsalesplatform.test.dao;

import java.util.List;

import com.fruitsalesplatform.test.entity.User;

//spring 通过mapper接口的方法使用mybatis的sql映射
//接口中定义的方法都是抽象的，不应该设置方法体
public interface TestDao {
	public  List<User> findUserByName(User user);
}
