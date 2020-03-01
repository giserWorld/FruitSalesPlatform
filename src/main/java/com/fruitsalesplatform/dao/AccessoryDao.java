package com.fruitsalesplatform.dao;

import java.util.Map;

import com.fruitsalesplatform.entity.Accessory;


//定 数据表访sql接口
public interface AccessoryDao extends BaseDao<Accessory>{
	//添加新的sql方法
	public int count(Map map);//根据条件统计数量
	
}
