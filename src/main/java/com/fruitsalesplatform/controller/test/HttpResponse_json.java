package com.fruitsalesplatform.controller.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//httpReponse响应数据为json类型
@Controller
public class HttpResponse_json {
	
	@RequestMapping("test/response_json.action")
	@ResponseBody
	public Map response_json(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", "小红");
		map.put("password", "123456");
		return map;
	}
}
