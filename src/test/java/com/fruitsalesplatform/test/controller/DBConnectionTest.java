package com.fruitsalesplatform.test.controller;

import jdk.nashorn.internal.ir.annotations.Reference;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fruitsalesplatform.test.entity.User;

/**
 ***************测试数据库连接情况************** 
 *1.@Autowired自动注入方式比 “applicationContext”更快
 *2.通过“SqlSessionDaoSupport”获取“sqlSession(SqlSessionTemplate)”实例，是临时的
 *通过sqlSession执行sql后，自动关闭，所以不需要手动关闭sqlSession，更加方便
 * */


@Controller
public class DBConnectionTest extends SqlSessionDaoSupport{
	
	//测试数据库连接
	@RequestMapping("/test/dbTest.action")
	public void testDbConnection(){
		System.out.println("*****************数据库连接测试start*****************");
		//1.获取mybatis原生sqlSession对象
		//SqlSession sqlSession=getSqlession_mybatis();
		
		//2.通过@Autowired自动注入方式获取原生 sqlSession对象
		//SqlSession sqlSession=getSqlession_autowired();
		
		//3.通过“SqlSessionDaoSupport”方式获取spring封装的sqlSession对象
		SqlSession sqlSession=getSqlession_SqlSessionDaoSupport();
		
		
		if(sqlSession!=null){
			Configuration mybatisConfig=sqlSession.getConfiguration();
			//获取mybaitis SQL映射配置的数量
			int num=mybatisConfig.getMappedStatements().size();
			System.out.println("Mybatis-数据连接成功！目前SQL配置数据为"+num);
		}
		else{
			System.out.println("Mybatis-数据连接失败！");
		}
		//sqlSession.close();//openSession()打开连接，就需要关闭连接
		System.out.println("*****************数据库连接测试end*****************");
	}
	
	//3.通过"SqlSessionDaoSupport"获取sqlSession对象(xml的SqlSessionFactory bean.id="sessionFactory")
	
	@Autowired//从xml中获取“sessionFactory”，并且传给setSqlSessionFactory()方法
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public SqlSession getSqlession_SqlSessionDaoSupport(){
		//获取临时sqlSession(SqlSessionTemplate)
		SqlSession sqlSession=getSqlSession();//“SqlSessionDaoSupport”继承的方法
		return sqlSession;
	}
	

	//2.通过@Autowired自动注入方式获取原生 sqlSession对象(需要借助web服务器，junit运行不起来)
	@Autowired
	SqlSessionFactory sessionFactory;
	public SqlSession getSqlession_autowired(){
		//mybatis原生sqlSession
		SqlSession sqlSession=sessionFactory.openSession();
		return sqlSession;
	}
	
	//1.通过spring xml配置文件获取原生SqlSession
	public SqlSession getSqlession_mybatis(){
		String resource="beans.xml";//spring配置文件url
		//从spring配置文件中读取数据，并且创建applicationContext实例
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext(resource);
		//获取id为“sessionFactory”获取配置的“org.mybatis.spring.SqlSessionFactoryBean”类实例
		SqlSessionFactory sqlSessionFactory=(SqlSessionFactory)applicationContext.getBean("sessionFactory");
		//获取mybatis核心SqlSession类实例对象
		SqlSession sqlSession=sqlSessionFactory.openSession();//mybatis原生sqlSession
		return sqlSession;
	}
	

}
