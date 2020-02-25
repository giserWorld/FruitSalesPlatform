package com.fruitsalesplatform.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitsalesplatform.dao.BaseDao;


/**************************基础sql实现类*****************************
 *1.自动装配SqlSessionFactory，提供获取SqlSession实例的方法getSqlSession() 
 *2.定义了基础的(增、删、改、查语句的实现)
 *3.提供了设置数据访问接口对应的mapper工作空间的方法setNs()
 **/
public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T>{

	
	private String ns;//命名空间
	
	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}

	@Override
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	
	@Override
	//通过数据id获取数据
	public T get(Serializable id) {
		return this.getSqlSession().selectOne(ns+".get",id);
	}

	@Override
	public List<T> find(Map map) {
		//mapper接口
		String sqlId=this.ns+".find";
		List<T> oList=this.getSqlSession().selectList(ns+".find",map);
		return oList;
	}

	@Override
	public void insert(T entity) {
		this.getSqlSession().insert(ns+".insert",entity);
		
	}

	@Override
	public void update(T entity) {
		this.getSqlSession().insert(ns+".update",entity);
	}

	@Override
	public void deleteById(Serializable id) {
		this.getSqlSession().delete(ns+".deleteById",id);
	}

	@Override
	public void delete(Serializable[] ids) {
		this.getSqlSession().delete(ns+".delete",ids);
		
	}
	
}
