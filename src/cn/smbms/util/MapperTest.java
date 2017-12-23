package cn.smbms.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.smbms.bean.Address;
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
	static Map<String, String> userMap = new HashMap<String, String>();

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
			// 用指定URL的方式进行数据库的操作
			// list =
			// sqlSession.selectList("cn.smbms.dao.user.UserMapper.getUserListByUserName",user);
			// 使用Mapper接口对数据库进行操作
			list = sqlSession.getMapper(UserMapper.class)
					.getUserListByUserName("孙", 3);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
		logger.debug(list.size());
		for (User user : list) {
			logger.debug(user.toString());
		}

	}

	@Test
	public void testGetUserListByMap() {
		sqlSession = MyBatisUtil.createSqlSession();
		userMap.put("uName", "赵");
		userMap.put("uRole", "3");
		try {
			list = sqlSession.getMapper(UserMapper.class).getUserListByMap(
					userMap);
		} catch (Exception e) {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
		for (User user : list) {
			logger.debug(user.toString());
		}
	}

	/**
	 * 
	 * resultMap得到数据结果！
	 * 
	 * @author 牛牛
	 * 
	 * @date 2017-12-22
	 * 
	 */
	@Test
	public void TestGetUserListResMap() {
		sqlSession = MyBatisUtil.createSqlSession();
		try {
			User user = new User();
			user.setUserName("赵");
			user.setUserRole(3);
			list = sqlSession.getMapper(UserMapper.class).getUserList(user);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
		for (User user : list) {
			logger.debug(user.toString() + user.getAge());
		}
	}

	@Test
	public void testAdd() {
		sqlSession = MyBatisUtil.createSqlSession();
		int count = 0;
		try {
			User user = new User();
			user.setId(18);
			user.setUserName("测试用户");
			user.setUserCode("ceshi");
			user.setUserPassword("123456");
			user.setGender(1);
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd")
					.parse("2017-11-12"));
			user.setPhone("11111111111");
			user.setAddress("测试地址");
			user.setUserRole(2);
			user.setCreatedBy(1);
			user.setCreationDate(new Date());
			count = sqlSession.getMapper(UserMapper.class).add(user);
			// int i=2/0;
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		} finally {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
		logger.debug(count);
	}

	@Test
	public void testChange() {

		int count = 0;
		try {
			User user = new User();
			user.setId(20);
			user.setUserName("测试修改用户");
			user.setAddress("测试修改地址");
			user.setModifyDate(new Date());
			user.setModifyBy(1);
			sqlSession = MyBatisUtil.createSqlSession();
			count = sqlSession.getMapper(UserMapper.class).change(user);
			// int i=2/0;
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		} finally {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
		logger.debug(count);
	}

	@Test
	public void TestUpdatePass() {
		sqlSession = MyBatisUtil.createSqlSession();
		try {
			int count = sqlSession.getMapper(UserMapper.class).updatePass(18,
					"8888");
			sqlSession.commit();
			if (count > 0) {
				logger.debug("更改成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			sqlSession.rollback();
			e.getStackTrace();
		} finally {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
	}

	@Test
	public void TestDeleteUserById() {
		sqlSession = MyBatisUtil.createSqlSession();
		int count = 0;
		try {
			count = sqlSession.getMapper(UserMapper.class).deleteUserById(19);
			sqlSession.commit();
		} catch (Exception e) {
			e.getStackTrace();
			sqlSession.rollback();
			count = 0;
		}
		if (count > 0) {
			logger.debug("删除成功");
		} else {
			logger.debug("删除失败");
		}
	}

	@Test
	public void TestGetUserListByUserRole() {
		sqlSession = MyBatisUtil.createSqlSession();
		list = sqlSession.getMapper(UserMapper.class).getUserByUserRole(3);
		MyBatisUtil.closeSQLSession(sqlSession);
		for (User user : list) {
			logger.debug(user.getUserName() + "" + user.getRole().getRoleName()
					+ user.getUserRole());
		}
	}

	@Test
	public void TestGetAddressByUserId() {
		sqlSession = MyBatisUtil.createSqlSession();
		list = sqlSession.getMapper(UserMapper.class).getUserAddressList(1);
		MyBatisUtil.closeSQLSession(sqlSession);
		for (User user : list) {
			for (Address address : user.getAddresslist()) {
				logger.debug(user.getUserName() + address.getAddressDesc()
						+ address.getTel() + address.getContact());
			}

		}
	}

	@Test
	public void TestGetUserListByRoleIdArray() {
		Integer[] roleIds = { 2, 3 };
		sqlSession = MyBatisUtil.createSqlSession();
		list = sqlSession.getMapper(UserMapper.class)
				.getUserByRoleId_foreach_array(roleIds);
		MyBatisUtil.closeSQLSession(sqlSession);
		for (User user : list) {
			logger.debug(user.toString());
		}
	}

	@Test
	public void TestGetUserListByRoleIdList() {
		List<Integer> roleIdList = new ArrayList<Integer>();
		roleIdList.add(2);
		roleIdList.add(3);
		sqlSession = MyBatisUtil.createSqlSession();
		list = sqlSession.getMapper(UserMapper.class)
				.getUserByRoleId_foreach_list(roleIdList);
		MyBatisUtil.closeSQLSession(sqlSession);
		for (User user : list) {
			logger.debug(user.toString());
		}
	}

	@Test
	public void TestGetUserListByConditionMap() {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		List<Integer> roleId = new ArrayList<Integer>();
		roleId.add(2);
		conditionMap.put("gender",2);
		conditionMap.put("roleIds", roleId);
		sqlSession = MyBatisUtil.createSqlSession();
		list = sqlSession.getMapper(UserMapper.class)
				.getUserByConditionMap_foreach_map(conditionMap);
		logger.debug(list.size());
		MyBatisUtil.closeSQLSession(sqlSession);
		for (User user : list) {
			logger.debug(user.toString());
		}
	}
	@Test
	public void TestGetUserListByRoleIdMap() {
		Map<String, Object> roleMap = new HashMap<String, Object>();
		List<Integer> roleId = new ArrayList<Integer>();
		roleId.add(2);
		roleId.add(1);
		roleMap.put("roleIds", roleId);
		sqlSession = MyBatisUtil.createSqlSession();
		list = sqlSession.getMapper(UserMapper.class).getUserByRoleId_foreach_map(roleMap);
		logger.debug(list.size());
		MyBatisUtil.closeSQLSession(sqlSession);
		for (User user : list) {
			logger.debug(user.toString());
		}
	}
}
