package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.User;

public interface UserMapper {
	@Select("SELECT * FROM user")
	@Results({
        @Result(property = "userName",  column = "user_name"),
        @Result(property = "userPassword", column = "user_password")
    })
    List<User> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
        @Result(property = "userName",  column = "user_name"/*, javaType = UserSexEnum.class*/),
        @Result(property = "userPassword", column = "user_password")
    })
    User getOne(Long id);

    @Insert("INSERT INTO user(user_name,user_password) VALUES(#{userName}, #{userPassword})")
    void insert(User user);

    @Update("UPDATE user SET userName=#{userName},user_password=#{userPassword} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);
    
    @Update("${sql}")
    void createTable(@Param("sql") String sql);

}