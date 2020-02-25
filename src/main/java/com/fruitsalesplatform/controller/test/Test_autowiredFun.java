package com.fruitsalesplatform.controller.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fruitsalesplatform.dao.RetailerDao;
import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.service.RetailerService;


//测试@autowired是否成功
@Controllerpublic class Test_autowiredFun {
	//dao层
	@Autowired
	RetailerDao retailerDaoImpl;
	//service层
	@Autowired
	RetailerService retailerServiceImpl;
	
	@RequestMapping("test/autowiredTest.action")
	public void testAutofun(){
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", "石恩华");
		
		List<Retailer> dataList=retailerServiceImpl.find(map);
		if(dataList.size()>0)System.out.println("-----@Autowired测试成功------");
	}
}
