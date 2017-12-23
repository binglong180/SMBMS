package cn.smbms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.bean.User;

public interface UserMapper {
	public List<User> getUserList();
	public List<User> getUserListByUserName(@Param("userName")String userName,@Param("userRole")Integer userRole);
	public List<User> getUserListByMap(Map<String,String> userMap);
	public List<User> getUserList(User User);
	public int add(User user);
	public int change(User user);
	public int updatePass(@Param("id")Integer id,@Param("userPassword")String userPassword);
	public int deleteUserById(@Param("id")Integer id);
	public List<User> getUserByUserRole(@Param("userRole")Integer userRole);
	public List<User> getUserAddressList(@Param("id")Integer id);
	public List<User> getUserByRoleId_foreach_array(Integer [] roleIds);
}
