package cn.smbms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.smbms.bean.Provider;
import cn.smbms.bean.User;
import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.dao.user.UserMapper;

public class MapperTest {
	static Logger logger = Logger.getLogger(MapperTest.class);
	String resource = "mybatis-config.xml";
	InputStream is = null;
	SqlSession sqlSession = null;
	static List<User> list = new ArrayList<User>();
	static Map<String,String> userMap=new HashMap<String, String>();
	@Test
	public void testMapper() {
		try {
			is = Resources.getResourceAsStream(resource);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder()
					.build(is);
			int count = 0;
			sqlSession = factory.openSession();
			count = sqlSession.selectOne("cn.smbms.dao.user.UserMapper.count");
			logger.debug(count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	@Test
	public void testProviderMapper() {
		try {
			int count = 0;
			is = Resources.getResourceAsStream(resource);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder()
					.build(is);
			sqlSession = factory.openSession();
			count = sqlSession
					.selectOne("cn.smbms.dao.provider.ProviderMapper.count");
			logger.debug(count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 使用select来操作数据
	 */
	// @Test
	// public void testGetUserList() {
	// SqlSession sqlSession = null;
	// List<User> list = new ArrayList<User>();
	// sqlSession = MyBatisUtil.createSqlSession();
	// try {
	// list = sqlSession.selectList("cn.smbms.dao.user.UserMapper.getUserList");
	// } catch (Exception e) {
	// // TODO: handle exception
	// } finally {
	// MyBatisUtil.closeSQLSession(sqlSession);
	// }
	// for (User user : list) {
	// logger.debug(user.toString());
	// }
	// }
	/**
	 * 
	 * 使用Mapper接口方式来实现数据操作！
	 * 
	 * @author 牛牛
	 * 
	 * @date 2017-12-19
	 * 
	 */
	@Test
	public void testGetUserList() {
		SqlSession sqlSession = null;

		sqlSession = MyBatisUtil.createSqlSession();
		try {
			list = sqlSession.getMapper(UserMapper.class).getUserList();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
		for (User user : list) {
			logger.debug(user.toString());
		}
	}

	// @Test
	// public void testGetProviderList(){
	// SqlSession sqlSession=null;
	// List <Provider> list=new ArrayList<Provider>();
	// try {
	// sqlSession=MyBatisUtil.createSqlSession();
	// list=sqlSession.selectList("cn.smbms.dao.provider.ProviderMapper.GetProviderList");
	// for (Provider provider : list) {
	// logger.debug(provider);
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// }finally{
	// MyBatisUtil.closeSQLSession(sqlSession);
	// }
	// }
	@Test
	public void testGetProviderList() {
		SqlSession sqlSession = null;
		List<Provider> list = new ArrayList<Provider>();
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			list = sqlSession.getMapper(ProviderMapper.class).getProviderList();
			for (Provider provider : list) {
				logger.debug(provider);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
	}

	@Test
	public void testGetUserListByUserName() {
		sqlSession = MyBatisUtil.createSqlSession();
		try {
			User user = new User();
			user.setUserName("赵");
			user.setUserRole(3);
			//用指定URL的方式进行数据库的操作
			//list = sqlSession.selectList("cn.smbms.dao.user.UserMapper.getUserListByUserName",user);
			//使用Mapper接口对数据库进行操作
			list=sqlSession.getMapper(UserMapper.class).getUserListByUserName(user);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			MyBatisUtil.closeSQLSession(sqlSession);
		}

		for (User user : list) {
			logger.debug(user.toString());
		}

	}
	@Test
	public void testGetUserListByMap(){
		sqlSession=MyBatisUtil.createSqlSession();
		userMap.put("uName", "赵");
		userMap.put("uRole", "3");
		try {
			list=sqlSession.getMapper(UserMapper.class).getUserListByMap(userMap);
		} catch (Exception e) {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
		for (User user : list) {
			logger.debug(user.toString());
		}
	}
}
