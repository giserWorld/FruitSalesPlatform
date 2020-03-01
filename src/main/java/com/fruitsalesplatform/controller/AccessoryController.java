package com.fruitsalesplatform.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fruitsalesplatform.entity.Accessory;
import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatform.service.AccessoryService;

//水果货物管理
@Controller
public class AccessoryController extends BaseController{
	
	@Autowired
	AccessoryService accessoryService;
	
	
	//添加新数据
	@RequestMapping("accessory/addData.action")
	public void addRetailer(Model model,Accessory accessory,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String uuid=UUID.randomUUID().toString();//uid
		accessory.setAccessoryid(uuid);
		//获取时间
		SimpleDateFormat date_format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime=date_format.format(new Date());
		accessory.setCreatetime(currentTime);
		String fruitid=accessory.getFruitid();
		accessoryService.insert(accessory);
		response.sendRedirect("./list.action?fruitid="+fruitid);
	}
	
	//通过id删除数据接口
/*	@RequestMapping("commodities/delete.action")
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
	}*/
	
	//批量删除数据
	@RequestMapping("accessory/deleteList.action")
	@ResponseBody
	public Object deleteAccessory(Model model,
			@RequestBody Map<String,Object> map,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//将请求body参数映射为Map类型
		String fruitid=(String)map.get("fruitid");
		List<String> dataList=(List)map.get("arrays");//id list
		String[] strArray=new String[dataList.size()];
		dataList.toArray(strArray);//list to array

		accessoryService.delete(strArray);
		
		Map<String,Object> reponseMap=new HashMap<String,Object>();
		reponseMap.put("mgs","获取数据成功！");
		reponseMap.put("code","0");
		reponseMap.put("data","");
		return reponseMap;
	}
	
	//跳转到列表页面
	@RequestMapping("accessory/list.action")
	public String list(Model model,Accessory accessory,
			String startTime,String endTime){
		
		String fruitid=accessory.getFruitid();
		Map<String,Object> map=new HashMap<>();
		map.put("fruitid", fruitid);
	    
  		
  		//执行sql查询
  		List<Accessory> dataList=accessoryService.find(map);
  		model.addAttribute("list",dataList);
		return "/accessory/accessoryHome.jsp";
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
