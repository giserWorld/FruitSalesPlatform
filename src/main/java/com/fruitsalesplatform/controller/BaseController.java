package com.fruitsalesplatform.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

//Controller层基础核心类，所有的controller都需要继承该类
public abstract class BaseController {
	
	//在接口方法之前调用
	@InitBinder
	//此方法用于日期的装换，若页面日期格式转换错误，将错误报
	public void initBinder(WebDataBinder binder){
		//2020-02-03时间格式
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");//时间格式化工具
		dateFormat.setLenient(true);
		//创建自定义日期编辑器，自动将web数据中的date类型参数值，转为自定格式的数据
		CustomDateEditor customDateEditor=new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class,customDateEditor);
	}
	
	
}
