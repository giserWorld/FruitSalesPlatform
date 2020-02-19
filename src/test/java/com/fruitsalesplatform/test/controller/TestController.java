package com.fruitsalesplatform.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fruitsalesplatform.test.entity.User;
import com.fruitsalesplatform.test.service.TestService;

//控制层，请求接口映射
@Controller
public class TestController {
	
	@InitBinder
	//在请求“@RequestMapping”方法之前调用
	public void initBinderFun(){
		System.out.println("***********@InitBinder注解的方法被调用了！*********");
	}
	
	
	//自动装配，获取service层暴露的bean对象
	@Autowired
	private TestService testServiceImpl;//自动装配接口类型
	
	@RequestMapping("/user/findUser.action")
	public String findUser(User user,Model model){
		//创建use实例
		List <User> userList=testServiceImpl.findUserByName(user);
		model.addAttribute("userList", userList);
		
		System.out.println("***********@RequestMapping注解的方法被调用了！*********");
		return "/test/test.jsp";
	}
	
	@RequestMapping("toPage.action")
	public String toPage(){
		return "/test/test.jsp";
	}
	
}
