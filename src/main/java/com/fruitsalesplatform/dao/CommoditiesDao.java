package com.fruitsalesplatform.dao;

import java.util.Map;

import com.fruitsalesplatform.entity.Commodities;


//定义 Commodities数据访sql接口
public interface CommoditiesDao extends BaseDao<Commodities>{
	//添加新的sql方法
	public int count(Map map);//根据条件统计数量
	
}
