package com.elextec.bi.mapper;

import com.elextec.bi.common.entity.PageQuery;
import com.elextec.bi.entity.BiUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BiUserMapper {

    @Select("SELECT * FROM bi_user WHERE user_name = #{userName}")
    @Results(id = "biUserMap",
    	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "userName", column = "user_name"),
	    @Result(property = "userPassword", column = "user_password"),
	    @Result(property = "fullName", column = "full_name"), 
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"), 
	    @Result(property = "creater", column = "creater"),
//	    @Result(property = "department", column = "department_id"),
//	    	one = @One(select = "com.elextec.bi.mapper.DepartmentMapper.findDepartmentById") ),
	    @Result(property = "roles", column = "id",
	    	many = @Many(select = "com.elextec.bi.mapper.BiRoleMapper.findRolesByUserId") )
    	})
    List<BiUser> findUserByName(String userName);

    @Select("SELECT * FROM bi_user")
    @Results(id = "biUserMapOnly",
    	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "userName", column = "user_name"),
	    @Result(property = "userPassword", column = "user_password"),
	    @Result(property = "fullName", column = "full_name"), 
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") 
	})
    List<BiUser> findAll();

    @Select("SELECT * FROM bi_user WHERE id = #{userId}")
    @ResultMap("biUserMap")
    BiUser findUserById(String userId);

    @Insert("INSERT INTO bi_user(id,user_name,user_password,full_name,department_id,status,creater,create_time)"
	    + " VALUES(sys_guid(), #{userName}, #{userPassword}, #{fullName,jdbcType=VARCHAR}, "
	    + "#{department.id,jdbcType=VARCHAR}, #{status}, #{creater}, sysdate)")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    @ResultMap("biUserMap")
    void insert(BiUser user);

    @Update("UPDATE bi_user SET full_name=#{fullName},user_password=#{userPassword},"
	    + "department_id=#{department.id,jdbcType=VARCHAR},status=#{status} WHERE id =#{id}")
    void update(BiUser user);

    @Delete("DELETE FROM bi_user WHERE id =#{id}")
    void delete(String id);

    @Update("${sql}")
    void createTable(@Param("sql") String sql);

    /**
     * 新增用户的角色信息
     * 
     * @param user
     */
    @InsertProvider(type = BiMapperProvider.class, method = "addUserRoles")
    void addUserRoles(@Param("user") BiUser user);

    /**
     * 根据用户ID，删除用户的角色信息
     * 
     * @param userId
     */
    @Delete("DELETE FROM bi_user_role WHERE user_id=#{userId}")
    void delUserRoles(String userId);

    @SelectProvider(type = BiMapperProvider.class, method = "findUserByPage")
    @ResultMap("biUserMapOnly")
    List<BiUser> findUserByPage(@Param("user") BiUser user, @Param("page") PageQuery pageQuery);

    @SelectProvider(type = BiMapperProvider.class, method = "findUserCount")
    int findCount(@Param("user") BiUser user);
}