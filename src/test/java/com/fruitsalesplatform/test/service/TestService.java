package com.fruitsalesplatform.test.service;

import java.util.List;

import com.fruitsalesplatform.test.entity.User;

//调用dao层中数据访问的方法
public interface TestService {
	public List<User> findUserByName(User user);
}
