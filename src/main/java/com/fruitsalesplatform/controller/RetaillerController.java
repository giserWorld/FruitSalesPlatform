package com.fruitsalesplatform.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.myToolkit.MyJavaUtility;
import com.fruitsalesplatform.service.RetailerService;

//零售商管理
@Controller
public class RetaillerController extends BaseController{
	
	@Autowired
	RetailerService retailerServiceImpl;
	
	
	//修改零售商信息
	@RequestMapping("retailer/delete.action")
	@ResponseBody
	public Object deleteRetailer(Model model,Retailer retailer,
			HttpServletRequest request,HttpServletResponse response){
		
		//1.通过request获取参数,通过参数名获取参数值
		String id=request.getParameter("retailerid");
		String startPage=request.getParameter("startPage");
		String currentPage=request.getParameter("currentPage");
		String pageSize=request.getParameter("pageSize");
		//2.获取所有的参数名字
		Enumeration paramNameSet=request.getParameterNames();
		//3.获取所有的参数名字
	    Enumeration paramNameSet=request.getParameterNames();
		
		Map<String,Object> map=new HashMap<>();
		map.put("mgs","获取更新成功！");
		map.put("code","0");
		map.put("data","");
		return map;
	}
	
	
	//修改零售商信息
	@RequestMapping("retailer/updateRetailer.action")
	@ResponseBody
	public Object updateRetailer(Model model,@RequestBody Retailer retailer){
		retailerServiceImpl.update(retailer);
		Map<String,Object> map=new HashMap<>();
		
		map.put("mgs","获取更新成功！");
		map.put("code","0");
		map.put("data","");
		return map;
	}
	
	//根据id获取详情
	@RequestMapping("retailer/getDetailById.action")
	public @ResponseBody Retailer  getDetailById(@RequestBody String json) throws Exception{
		//通过json解析器，解析为json字符串
		JsonMapper jsonMapper=new JsonMapper();
		//json字符串转map
		Map<String,Object> map=new HashMap<String,Object>();
		map=jsonMapper.readValue(json,new TypeReference<Map<String,Object>>(){});
		
		String id=(String)map.get("id");//获取参数id
		//获取id获取零售商信息
		Retailer retailer=retailerServiceImpl.get(id);
		return retailer;//返回json数据
	}
	
	//跳转到列表页面
	@RequestMapping("retailer/list.action")
	public String list(Model model,Retailer retailer,
			String startTime,String endTime){
		
		//将retailer实例转为map对象,构建查询条件
	    Map<String,Object> map=this.retailerToMap(retailer);
	    
	   //如果有startTime，endTime参数，则设置到retailer实例中
  		if(MyJavaUtility.isDefined(startTime)){
  			map.put("startTime", startTime);
  		}
  		if(MyJavaUtility.isDefined(endTime)){
  			map.put("endTime", endTime);
  		}
  		//执行sql查询
  		List<Retailer> dataList=retailerServiceImpl.find(map);
  		
  		int pageSize=retailer.getPageSize();//页面大小
  		int currentPage=retailer.getCurrentPage();//当前页面
  		int startPage=retailer.getOffset();//数据起始位置
  		int countNumber=retailerServiceImpl.count(map);//数据总数
  		//计算总页面数
  		int sumPageNumber=countNumber%pageSize==0?(countNumber%pageSize):((countNumber/pageSize)+1);
  		
  		model.addAttribute("list",dataList);//查询数据
  		model.addAttribute("currentPage",currentPage);
  		model.addAttribute("startPage",startPage);
  		model.addAttribute("pageSize",pageSize);
  		model.addAttribute("countNumber",countNumber);
  		model.addAttribute("sumPageNumber",sumPageNumber);
  		
		return "/retailer/retailerHome.jsp";
	}
	
	//将Retailer实例对象转为map对象
	public Map<String,Object> retailerToMap(Retailer retailer){
		
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("name", checkIsEmpty(retailer.getName()));
		map.put("telephone",checkIsEmpty(retailer.getTelephone()));
		map.put("address",checkIsEmpty(retailer.getAddress()));
		map.put("status",checkIsEmpty(retailer.getStatus()));
		map.put("createtime",checkIsEmpty(retailer.getCreatetime()));
		map.put("currentPage",checkIsEmpty(retailer.getCurrentPage()));
		map.put("startPage",checkIsEmpty(retailer.getStartPage()));
		map.put("pageSize",checkIsEmpty(retailer.getPageSize()));
		map.put("offset",checkIsEmpty(retailer.getOffset()));
		return map;
	}
	//参数类型可以为String、Integer
	public Object checkIsEmpty(Object param) {
		//如果参数为字符串类型
		if(param instanceof String){
			param=(String)param;//向下转型
			//三元表达式
			String res=param==null?null:("".equals(param)?null:"%"+param+"%");
			return res;
		}
		else if(param instanceof Integer){//参数为整型
			//向下转型为Integer
			param=(Integer)param;//向下转型
			//String str=param.toString();
			return param;
		}
		return null;
	}
	
}
