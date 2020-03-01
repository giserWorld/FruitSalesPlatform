package com.fruitsalesplatform.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.myToolkit.MyJavaUtility;
import com.fruitsalesplatform.service.CommoditiesService;

//水果货物管理
@Controller
public class CommoditiesController extends BaseController{
	
	@Autowired
	CommoditiesService commoditiesServiceImpl;
	
	
	//添加新数据
	@RequestMapping("commodities/addData.action")
	public String addRetailer(Model model,Commodities commodities,
			HttpServletRequest request,HttpServletResponse response){
		
		String uuid=UUID.randomUUID().toString();//uid
		commodities.setFruitid(uuid);;
		//获取时间
		SimpleDateFormat date_format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime=date_format.format(new Date());
		commodities.setCreatetime(currentTime);
		
		commoditiesServiceImpl.insert(commodities);
		Commodities query=new Commodities();
		return list(model, query,0.0,0.0, null, null);
	}
	
	//删除数据接口
	@RequestMapping("commodities/delete.action")
	public String deleteRetailer(Model model,
			HttpServletRequest request,HttpServletResponse response){
		
		//1.通过request获取参数,通过参数名获取参数值
		String id=request.getParameter("fruitid");
		String startPage=request.getParameter("startPage");
		String currentPage=request.getParameter("currentPage");
		String pageSize=request.getParameter("pageSize");
		commoditiesServiceImpl.deleteById(id);//删除数据
		
		Commodities query=new Commodities();
		
		query.setStartPage(Integer.valueOf(startPage));
		query.setCurrentPage(Integer.valueOf(currentPage));
		query.setPageSize(Integer.valueOf(pageSize));
		
		return list(model, query, 0.0, 0.0,null, null);
	}
	
	
	//修改零售商信息
	@RequestMapping("commodities/updateData.action")
	@ResponseBody
	public Object updateRetailer(Model model,@RequestBody Commodities commodities){
		//将请求body参数映射为实体类
		commoditiesServiceImpl.update(commodities);
		Map<String,Object> map=new HashMap<>();
		
		map.put("mgs","获取更新成功！");
		map.put("code","0");
		map.put("data","");
		return map;
	}
	
	//根据id获取详情
	@RequestMapping("commodities/getDetailById.action")
	@ResponseBody
	public Commodities getDetailById(@RequestBody Map<String,String> map){
		//将请求body参数映射为Map类型
		String id=map.get("id");
		//获取id获取水果商品信息
		Commodities commodities=commoditiesServiceImpl.get(id);
		return commodities;//返回json数据
	}
	
	//跳转到列表页面
	@RequestMapping("commodities/list.action")
	public String list(Model model,Commodities commodities,
			@RequestParam(defaultValue="0.0") double startPrice,
			@RequestParam(defaultValue="0.0") double endPrice,
			String startTime,String endTime){
		
		//将数据实例转为map对象,构建查询条件
	    Map<String,Object> map=this.CommoditiesToMap(commodities);
	    
	   //如果有startTime，endTime参数，则设置到retailer实例中
  		if(MyJavaUtility.isDefined(startTime)){
  			map.put("startTime", startTime);
  		}
  		if(MyJavaUtility.isDefined(endTime)){
  			map.put("endTime", endTime);
  		}
  		if(startPrice>0){
  			map.put("startPrice", startPrice);
  		}
  		if(endPrice>0){
  			map.put("endPrice", endPrice);
  		}
  		
  		//执行sql查询
  		List<Commodities> dataList=commoditiesServiceImpl.find(map);
  		
  		int pageSize=commodities.getPageSize();//页面大小
  		int currentPage=commodities.getCurrentPage();//当前页面
  		int startPage=commodities.getOffset();//数据起始位置
  		int countNumber=commoditiesServiceImpl.count(map);//数据总数
  		//计算总页面数
  		int sumPageNumber=countNumber%pageSize==0?(countNumber%pageSize):((countNumber/pageSize)+1);
  		
  		//搜索条件回显
  		model.addAttribute("commodities",commodities);
  		model.addAttribute("startPrice",startPrice);
  		model.addAttribute("endPrice",endPrice);
  		model.addAttribute("startTime",startTime);
  		model.addAttribute("endTime",endTime);
  		model.addAttribute("list",dataList);
  		
  		model.addAttribute("currentPage",currentPage);
  		model.addAttribute("startPage",startPage);
  		model.addAttribute("pageSize",pageSize);
  		model.addAttribute("countNumber",countNumber);
  		model.addAttribute("sumPageNumber",sumPageNumber);
  		
		return "/commodities/commoditiesHome.jsp";
	}
	
	//将Retailer实例对象转为map对象
	public Map<String,Object> CommoditiesToMap(Commodities commodities){
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("fruitid", checkIsEmpty(commodities.getFruitid()));
		map.put("name", checkIsEmpty(commodities.getName()));
		map.put("price",checkIsEmpty(commodities.getPrice()));
		map.put("locality",checkIsEmpty(commodities.getLocality()));
		map.put("createtime",checkIsEmpty(commodities.getCreatetime()));
		//分页属性
		map.put("currentPage",checkIsEmpty(commodities.getCurrentPage()));
		map.put("startPage",checkIsEmpty(commodities.getStartPage()));
		map.put("pageSize",checkIsEmpty(commodities.getPageSize()));
		map.put("offset",checkIsEmpty(commodities.getOffset()));
		return map;
	}
	//参数类型可以为String、Integer
	public Object checkIsEmpty(Object param) {
		if(param  instanceof String){
			Object res=param==null?null:("".equals(param)?null:"%"+param+"%");
			return res;	
		}
		else{
			return param;	
		}
	}
	
}
