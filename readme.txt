SMBMS1.0
技术点：
1、使用select进行数据操作
在Mapper.xml中配置
<select id="getUserListByUserName" resultType="User" parameterType="string">
		select * from smbms_user where userName like CONCAT
		('%',#{userName},'%') and userRole=#{userRole}
</select>


2、使用Mapper接口操作数据库多条件查询，参数为类对象
<select id="getUserList" resultType="User" parameterType="User">
		select * from smbms_user where userName like CONCAT ('%',#{uName},'%')
		and userRole=#{uRole}
</select>

Mapper接口中添加对应的接口
接口名与id名一致，参数和parameterType类型一致，resultType返回值类型；
public List<User> getUserList(User user);

3、使用Mapper接口操作数据库多条件查询，参数为Map
<select id="getUserList" resultType="User" parameterType="Map">
		select * from smbms_user where userName like CONCAT ('%',#{uName},'%')
		and userRole=#{uRole}
</select>

public List<User> getUserList(Map map);
这个方法与第二个方法相似只是参数为Map类型；
