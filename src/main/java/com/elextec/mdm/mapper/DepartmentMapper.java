package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
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
	@ResultMap("departmentMap")
    void insert(Department department);
	
	@Select("SELECT * FROM department ORDER BY depart_code")
	@Results(id="departmentMap",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "departCode",  column = "depart_code"),
        @Result(property = "departName", column = "depart_name"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
        @Result(property = "creater", column = "creater"),
        @Result(property = "parentId", column = "parent_id"),
        @Result(property = "departments", column = "id",
            	many = @Many(select = "com.elextec.mdm.mapper.DepartmentMapper.findDepartmentByParentId"))
    })
    List<Department> getAll();
	
	/**
	 * 获取所有最高级部门信息
	 * @return
	 */
	@Select("SELECT * FROM department WHERE parent_id is null ORDER BY depart_code")
	@ResultMap("departmentMap")
    List<Department> getSuperDepartment();
	
	/**
	 * 根据departmentId查找单个部门信息
	 * @param departmentId
	 * @return
	 */
	@Select("SELECT * FROM department WHERE id = #{departmentId}")
	@Results(id="departmentMapOnly",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "departCode",  column = "depart_code"),
        @Result(property = "departName", column = "depart_name"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
        @Result(property = "creater", column = "creater"),
        @Result(property = "parentId", column = "parent_id"),
    })
	Department findDepartmentById(int departmentId);
	
	/**
	 * 根据departmentId查找部门信息及下级部门信息
	 * @param departmentId
	 * @return
	 */
	@Select("SELECT * FROM department WHERE id = #{departmentId}")
	@ResultMap("departmentMap")
	Department findAllDepartmentsById(int departmentId);
	
	/**
	 * 根据parentId查找下级部门信息
	 * @param parentId
	 * @return
	 */
	@Select("SELECT * FROM department WHERE parent_id = #{parentId}")
	@ResultMap("departmentMap")
	Department findDepartmentByParentId(int parentId);
	
}
