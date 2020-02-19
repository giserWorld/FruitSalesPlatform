package com.fruitsalesplatform.test.db;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fruitsalesplatform.test.entity.User;

//测试数据库连接情况
public class DBConnectionTest {
	//mybatis配置文件url
	private String resource="beans.xml";
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
	//测试select 语句
	@Test
	public void testSelect(){
		sqlSession=getSqlession();//获取sqlSession对象
		//sql映射
		User user=sqlSession.selectOne("test.findUserByName","张三");
		
		
		System.out.println("取出用户信息：");
		System.out.println("账号："+user.getName());
		System.out.println("密码："+user.getPassword());
		System.out.println("姓名："+user.getUsername());
		System.out.println("电话："+user.getTelephone());
		
		//关闭sql会话
		sqlSession.close();
	}
	
	//获取mybatis sqlSession对象
	public SqlSession getSqlession(){
		//从spring配置文件中读取数据，并且创建applicationContext实例
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext(resource);
		//获取id为“sessionFactory”获取配置的“org.mybatis.spring.SqlSessionFactoryBean”类实例
		SqlSessionFactory sqlSessionFactory=(SqlSessionFactory)applicationContext.getBean("sessionFactory");
		//获取mybatis核心SqlSession类实例对象
		sqlSession=sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	
	//单元测试
	@Test
	public void testConnection(){
		//从spring配置文件中读取数据，并且创建applicationContext实例
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext(resource);
		
		//获取id为“sessionFactory”获取配置的“org.mybatis.spring.SqlSessionFactoryBean”类实例
		SqlSessionFactory sqlSessionFactory=(SqlSessionFactory)applicationContext.getBean("sessionFactory");
		//获取mybatis核心SqlSession类实例对象
		sqlSession=sqlSessionFactory.openSession();
		
		if(sqlSession!=null){
			Configuration mybatisConfig=sqlSession.getConfiguration();
			//获取mybaitis SQL映射配置的数量
			int num=mybatisConfig.getMappedStatements().size();
			System.out.println("Mybatis-数据连接成功！目前SQL配置数据为"+num);
		}
		else{
			System.out.println("Mybatis-数据连接失败！");
		}
		sqlSession.close();
	}
}
