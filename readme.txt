SMBMS1.0
�����㣺
1��ʹ��select�������ݲ���
��Mapper.xml������
<select id="getUserListByUserName" resultType="User" parameterType="string">
		select * from smbms_user where userName like CONCAT
		('%',#{userName},'%') and userRole=#{userRole}
</select>


2��ʹ��Mapper�ӿڲ������ݿ��������ѯ������Ϊ�����
<select id="getUserList" resultType="User" parameterType="User">
		select * from smbms_user where userName like CONCAT ('%',#{uName},'%')
		and userRole=#{uRole}
</select>

Mapper�ӿ�����Ӷ�Ӧ�Ľӿ�
�ӿ�����id��һ�£�������parameterType����һ�£�resultType����ֵ���ͣ�
public List<User> getUserList(User user);

3��ʹ��Mapper�ӿڲ������ݿ��������ѯ������ΪMap
<select id="getUserList" resultType="User" parameterType="Map">
		select * from smbms_user where userName like CONCAT ('%',#{uName},'%')
		and userRole=#{uRole}
</select>

public List<User> getUserList(Map map);
���������ڶ�����������ֻ�ǲ���ΪMap���ͣ�
