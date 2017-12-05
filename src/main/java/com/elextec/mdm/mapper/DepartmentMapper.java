package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.Department;

public interface DepartmentMapper {

	@Insert("INSERT INTO department(depart_code,depart_name,parent_id,status,creater)"
    		+ " VALUES(#{departCode}, #{departName}, #{parentId}, #{status}, #{creater})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Department department);
	
	@Select("SELECT * FROM department")
	@Results(id="departmentMap",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "departCode",  column = "depart_code"),
        @Result(property = "departName", column = "depart_name"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
        @Result(property = "creater", column = "creater"),
    })
    List<Department> getAll();
	
	@Select("SELECT * FROM department WHERE id = #{departmentId}")
	@ResultMap("departmentMap")
	Department findDepartmentById(int departmentId);
	
	
}
