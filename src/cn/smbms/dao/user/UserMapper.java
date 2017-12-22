package cn.smbms.dao.user;

import java.util.List;
import java.util.Map;

import cn.smbms.bean.User;

public interface UserMapper {
	public List<User> getUserList();
	public List<User> getUserListByUserName(User user);
	public List<User> getUserListByMap(Map<String,String> userMap);
	public List<User> getUserList(User User);
}
