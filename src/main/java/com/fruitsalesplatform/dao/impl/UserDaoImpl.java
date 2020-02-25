package com.fruitsalesplatform.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.dao.UserDao;
import com.fruitsalesplatform.entity.User;

//继承基础的sql查询接口
@Repository
public class UserDaoImpl extends BaseDaoImpl<User>implements UserDao{
	
	//构造方法，autowire自动注入时，自动调用构造函数
	public UserDaoImpl(){
		//设置mapper命名空间
		super.setNs("com.fruitsalesplatform.mapper.Usermapper");
	}
	
}
