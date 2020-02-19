package com.fruitsalesplatform.test.service.impl;

import org.springframework.stereotype.Service;

import com.fruitsalesplatform.test.entity.User;

//@Service注解自动创建层bean实例
@Service
public class TestBean_service {
	
	public void testAuto(){
		System.out.println("******************************");
		System.out.println("实现类自动注入@service!");
		System.out.println("******************************");
	}
}
