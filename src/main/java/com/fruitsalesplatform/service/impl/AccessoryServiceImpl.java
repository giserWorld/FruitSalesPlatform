package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.dao.AccessoryDao;
import com.fruitsalesplatform.entity.Accessory;
import com.fruitsalesplatform.service.AccessoryService;


//业务处理数据
@Service
public class AccessoryServiceImpl implements AccessoryService{

	@Autowired
	AccessoryDao accessoryDaoImpl;
	
	@Override
	public Accessory get(Serializable id) {
		// TODO Auto-generated method stub
		return accessoryDaoImpl.get(id);
	}

	@Override
	public List<Accessory> find(Map map) {
		// TODO Auto-generated method stub
		return accessoryDaoImpl.find(map);
	}

	@Override
	public void insert(Accessory entity) {
		// TODO Auto-generated method stub
		accessoryDaoImpl.insert(entity);
	}

	@Override
	public void update(Accessory entity) {
		// TODO Auto-generated method stub
		accessoryDaoImpl.update(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		accessoryDaoImpl.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		accessoryDaoImpl.delete(ids);
	}

	@Override
	public int count(Map map) {
		// TODO Auto-generated method stub
		return accessoryDaoImpl.count(map);
	}
	
	
	
}
