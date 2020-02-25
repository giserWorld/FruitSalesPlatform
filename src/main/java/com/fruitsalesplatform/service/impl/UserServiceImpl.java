package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.dao.impl.UserDaoImpl;
import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.service.UserService;


//业务处理数据
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDaoImpl userDaoImpl;
	
	
	@Override
	public User get(Serializable id) {
		System.out.println("autowired-UserServiceImpl成功");
		return userDaoImpl.get(id);
	}
	
	@Override
	public List<User> find(Map map) {
		return userDaoImpl.find(map);
	}

	@Override
	public void insert(User entity) {
		userDaoImpl.insert(entity);
	}

	@Override
	public void update(User entity) {
		userDaoImpl.update(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		userDaoImpl.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		userDaoImpl.delete(ids);
	}
	
}
