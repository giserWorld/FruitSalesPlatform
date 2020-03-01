package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.dao.CommoditiesDao;
import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatform.service.CommoditiesService;


//业务处理数据
@Service
public class CommoditiesServiceImpl implements CommoditiesService{

	@Autowired
	CommoditiesDao commoditiesDaoImpl;
	
	@Override
	public Commodities get(Serializable id) {
		// TODO Auto-generated method stub
		return commoditiesDaoImpl.get(id);
	}

	@Override
	public List<Commodities> find(Map map) {
		// TODO Auto-generated method stub
		return commoditiesDaoImpl.find(map);
	}

	@Override
	public void insert(Commodities entity) {
		// TODO Auto-generated method stub
		commoditiesDaoImpl.insert(entity);
	}

	@Override
	public void update(Commodities entity) {
		// TODO Auto-generated method stub
		commoditiesDaoImpl.update(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		commoditiesDaoImpl.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		commoditiesDaoImpl.delete(ids);
	}

	@Override
	public int count(Map map) {
		// TODO Auto-generated method stub
		return commoditiesDaoImpl.count(map);
	}
	
	
	
}
