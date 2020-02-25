package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.dao.RetailerDao;
import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.service.RetailerService;


//业务处理数据
@Service
public class RetailerServiceImpl implements RetailerService{

	@Autowired
	RetailerDao retailerDaoImpl;
	
	@Override
	public Retailer get(Serializable id) {
		// TODO Auto-generated method stub
		return retailerDaoImpl.get(id);
	}

	@Override
	public List<Retailer> find(Map map) {
		// TODO Auto-generated method stub
		return retailerDaoImpl.find(map);
	}

	@Override
	public void insert(Retailer entity) {
		// TODO Auto-generated method stub
		retailerDaoImpl.insert(entity);
	}

	@Override
	public void update(Retailer entity) {
		// TODO Auto-generated method stub
		retailerDaoImpl.update(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		retailerDaoImpl.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		retailerDaoImpl.delete(ids);
	}

	@Override
	public int count(Map map) {
		// TODO Auto-generated method stub
		return retailerDaoImpl.count(map);
	}
	
	
	
}
