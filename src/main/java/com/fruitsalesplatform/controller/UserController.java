package com.fruitsalesplatform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.myToolkit.MyJavaUtility;
import com.fruitsalesplatform.service.UserService;

//用户登录
@Controller
public class UserController {
	
	@Autowired
	UserService userServiceImpl;
	
	
	
	
	//用户注册
	@RequestMapping("user/register.action")
	public String userRegister(Model model,User user){
		
		//判断该用户是否已经注册
		Map<String,String> mapTemp=new HashMap<String,String>();
		mapTemp.put("username", user.getUsername());
		List<User> queryUser=userServiceImpl.find(mapTemp);
		if(queryUser!=null&&queryUser.size()>0){//用户已经存在
			model.addAttribute("errorMsg","注册失败，用户名已经被占用！");
		}
		else{//注册新用户到数据库
			if(MyJavaUtility.isDefined(user.getUsername())&&MyJavaUtility.isDefined(user.getPassword())){
				String uuid=UUID.randomUUID().toString();//uuid
				user.setUserid(uuid);//设置新用户的id
				userServiceImpl.insert(user);
				model.addAttribute("errorMsg","注册成功，请输入账号密码登录！");
				return "/login.jsp";
			}
			else{
				model.addAttribute("errorMsg","账号或密码不能为空！");
				return "/register.jsp";
			}
		}
		return "/register.jsp";
	}
	
	
	//跳转注册页面
	@RequestMapping("user/toRegister.action")
	public String toRegister(){
		return "/register.jsp";
	}
	
	
	//处理登录逻辑
	@RequestMapping("user/login.action")
	public String login(Model model,User user,HttpServletRequest request){
		//创建一个map对象
		Map<String,String> usermap=new HashMap<String,String>();
		usermap.put("username", user.getUsername());
		usermap.put("password", user.getPassword());
		List<User> userList=userServiceImpl.find(usermap);
		
		//判断登录用户在数据库中是否存在
		if(userList!=null && userList.size()>0){
			HttpSession httpsession=request.getSession();
			User loginUser=userList.get(0);
			httpsession.setAttribute("user",loginUser);
			
			//跳转到主页面
			model.addAttribute("user",loginUser);//页面数据模型
			return "/home.jsp";
		}
		else{
			model.addAttribute("errorMsg", "登录失败！账号或密码错误!");
			return "/login.jsp";
		}
	}
	
	//跳转登录页面
	@RequestMapping("user/toLogin.action")
	public String toLogin(){
		
		return "/login.jsp";
	}
	
}
