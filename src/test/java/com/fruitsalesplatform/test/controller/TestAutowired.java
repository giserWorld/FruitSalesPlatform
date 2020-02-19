package com.fruitsalesplatform.test.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitsalesplatform.test.service.impl.TestBean_service;

//测试@autowired自动注入
public class TestAutowired {

	@Autowired
	TestBean_service testUserServer;
	
	@Test
	public void autoWiredFun(){
		
		testUserServer.testAuto();
		System.out.println("666666666");
	}
}
