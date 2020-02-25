package com.fruitsalesplatform.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.test.dao.TestDao;
import com.fruitsalesplatform.test.entity.User;
import com.fruitsalesplatform.test.service.TestService;

//Service层通过“@Service注解”对外暴露的bean对象,以供@controller使用
@Service
public class TestServiceImpl implements TestService{

	@Autowired //(自动装配)获取Dao层通过“@Repository注解”对外暴露的bean对象
	private TestDao testDao;
	
	//@Override
	public List<User> findUserByName(User user) {
		return testDao.findUserByName(user);
	}
	
	public void autoBean_service() {
		System.out.println("---------Bean-@service---------");
	}
	
}
