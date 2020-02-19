package com.fruitsalesplatform.test.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.test.dao.TestDao;
import com.fruitsalesplatform.test.entity.User;


/*
 *    @Autowired注解自动注入sqlSessionFactory类，
 * 即加载spring的xml配置文件中的sqlSessionFactory配置，获得一个
 * 注入有数据源的对象
 * 	  在Service层中使用“@Autowired注解”或“@Resource注解”可以注入bean对象
 * bean对象来源：
 * 	   1）通过spring的xml配置文件中的bean标签设置，创建的bean对象
 * 	   2）Dao层通过“@Repository注解”对外暴露的bean对象
 * */


//mapper接口的实现类,Dao层通过“@Repository注解”对外暴露的bean对象
@Repository
public class TestDaoImpl implements TestDao{

	@Autowired//获取spring的xml配置文件中的bean标签
	private SqlSessionFactory sessionFactory;
	
	private SqlSession sqlSession=null;//mybatis核心类
	
	//获取sqlSession
	public SqlSession getSqlSession(){
		//如果sqlSession为空，则需要重新创建
		if(sqlSession==null){
			sqlSession=sessionFactory.openSession();
		}
		return sqlSession;
	}
	
	@Override
	public List<User> findUserByName(User user) {
		SqlSession sqlSession2=getSqlSession();
		String name=user.getName();
		List<User> uList=sqlSession2.selectList("test.findUserByName","%"+name+"%");
		return uList;
	}
	
}
