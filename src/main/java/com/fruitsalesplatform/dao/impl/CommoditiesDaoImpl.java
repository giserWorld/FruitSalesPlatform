package com.fruitsalesplatform.dao.impl;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.dao.CommoditiesDao;
import com.fruitsalesplatform.entity.Commodities;



//对retailer表定义需要查询的sql接口,即有一个mapper配置，应该有对应Java数据访问接口(dao)
@Repository
public class CommoditiesDaoImpl extends BaseDaoImpl<Commodities> implements CommoditiesDao{

	public CommoditiesDaoImpl(){
		//设置数据接口对应的mapper工作空间
		this.setNs("com.fruitsalesplatform.mapper.CommoditiesMapper");
	}
	//编写出基础sql语句的其他查询
	@Override
	public int count(Map map) {
		return this.getSqlSession().selectOne(this.getNs()+".count",map);
	}
	
}
