package com.fruitsalesplatform.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

//数据访问层，即Dao层
//基础查询语句，增、删、改、查
//泛型类,基础的Dao接口
public interface BaseDao<T>{
	public T get(Serializable id);//只查询一个数据，常用于修改
	public List<T> find(Map map);//根据条件查询多个结果
	public void insert(T entity);//插入，用实体作为参数
	public void update(T entity);//修改，用实体作为参数
	public void deleteById(Serializable id);//按id删除，删除一条；支持整型和字符串类型id
	public void delete(Serializable[] ids);//批量删除，删除一条；支持整型和字符串类型id
	
}
