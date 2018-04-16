package com.elextec.mdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.elextec.mdm.common.entity.PageQuery;
import com.elextec.mdm.entity.User;

public interface UserMapper extends BaseMapper<User>{

	@Select("SELECT * FROM mdm_user WHERE id = #{userId}")
	@Results(id = "userMap", 
	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "userName", column = "user_name"),
	    @Result(property = "userPassword", column = "user_password"),
	    @Result(property = "fullName", column = "full_name"), 
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"), 
	    @Result(property = "creater", column = "creater"),
	    @Result(property = "department", column = "department_id",
	    	one = @One(select = "com.elextec.mdm.mapper.DepartmentMapper.findDepartmentById") ),
	    @Result(property = "roles", column = "id",
	    	many = @Many(select = "com.elextec.mdm.mapper.RoleMapper.findRolesByUserId") ) 
	})
    User findUserById(String userId);
	
	@Select("SELECT * FROM mdm_user")
    @Results(id = "userMapOnly", 
    	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "userName", column = "user_name"),
	    @Result(property = "userPassword", column = "user_password"),
	    @Result(property = "fullName", column = "full_name"), 
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") 
	})
    List<User> findAll();
	
    @Select("SELECT * FROM mdm_user WHERE user_name = #{userName}")
    @ResultMap("userMap")
    List<User> findUserByName(String userName);

    @Insert("INSERT INTO mdm_user(id,user_name,user_password,full_name,department_id,status,creater,create_time)"
	    + " VALUES(#{id}, #{userName}, #{userPassword}, #{fullName,jdbcType=VARCHAR}, "
	    + "#{dpartmentId,jdbcType=VARCHAR}, #{status}, #{creater}, sysdate)")
    @SelectKey(before = true, keyProperty = "id",
		resultType = String.class, statementType = StatementType.STATEMENT,
		statement="SELECT sys_guid() FROM dual")
    void insert(User user);

    @Update("UPDATE mdm_user SET user_name=#{userName},full_name=#{fullName},user_password=#{userPassword},"
	    + "department_id=#{department.id,jdbcType=VARCHAR},status=#{status} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM mdm_user WHERE id =#{id}")
    void delete(String id);

    @Update("${sql}")
    void createTable(@Param("sql") String sql);

    /**
     * 新增用户的角色信息
     * @param user
     */
    @InsertProvider(type = MapperProvider.class, method = "addUserRoles")
    void addUserRoles(@Param("user") User user);

    /**
     * 根据用户ID，删除用户的角色信息
     * @param userId
     */
    @Delete("DELETE FROM mdm_user_role WHERE user_id=#{userId}")
    void delUserRoles(String userId);

    @SelectProvider(type = MapperProvider.class, method = "findEntityByPage")
	@ResultMap("userMapOnly")
    List<User> findByPage(@Param("queryParam") Map<String,String> map, @Param("page") PageQuery pageQuery);
}