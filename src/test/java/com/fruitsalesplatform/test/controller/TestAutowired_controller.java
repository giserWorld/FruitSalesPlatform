package com.fruitsalesplatform.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fruitsalesplatform.dao.impl.UserDaoImpl;
import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.service.UserService;

//测试@autowired自动注入
@Controller
public class TestAutowired_controller {
	
	@InitBinder//请求映射方法必须包含参数才可以，否则不会调用
	public void initBinder(){
		System.out.println("//**************控制层start:@Controler****************//");
	}
	
	//@Autowired
	//TestBean_service testBean_service;//@service层自动注入bean
	
	//@Autowired
	//UserDaoImpl userDaoImpl;//@Repository层自动注入bean
	
	@Autowired//使用 接口注入，否则可能会报错
	UserService userServiceImpl;//@service层自动注入bean
	
	@RequestMapping("/test/testAuto.action")
	public void autoWiredFun(Model model,User user){
		//testBean_service.testAuto();//调用自动注入@service层bean
		//userDaoImpl.testDao_autowired();
		
		//查询数据库数据
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", "张三");
		List<User> userList2=userServiceImpl.find(map);
		
	}
}
