package com.fruitsalesplatform.test.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.InitBinder;

import com.fruitsalesplatform.test.entity.User;

//@Service注解自动创建层bean实例
@Service
public class TestBean_service {
	
	public void testAuto(){
		System.out.println("@Service层的暴露bean[TestBean_service]!");
	}
}
