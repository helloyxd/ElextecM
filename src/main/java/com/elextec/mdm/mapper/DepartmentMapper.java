package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.Department;

public interface DepartmentMapper {

	@Insert("INSERT INTO mdm_department(id,depart_code,depart_name,parent_id,status,creater,create_time)"
    		+ " VALUES(sys_guid(), #{departCode}, #{departName}, #{parentId,jdbcType=VARCHAR}, #{status}, #{creater}, sysdate)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
	@ResultMap("departmentMap")
    void insert(Department department);
	
	@Update("UPDATE mdm_department SET depart_code=#{departCode},depart_name=#{departName},parent_id=#{parentId,jdbcType=VARCHAR} WHERE id=#{id}")
	void update(Department department);
	
	@Select("SELECT * FROM mdm_department ORDER BY depart_code")
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
    List<Department> findAll();
	
	/**
	 * 获取所有最高级部门信息
	 * @return
	 */
	@Select("SELECT * FROM mdm_department WHERE parent_id is null ORDER BY depart_code")
	@ResultMap("departmentMap")
    List<Department> findSuperDepartments();
	
	/**
	 * 根据departmentId查找单个部门信息
	 * @param departmentId
	 * @return
	 */
	@Select("SELECT * FROM mdm_department WHERE id = #{departmentId}")
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
	Department findDepartmentById(String departmentId);
	
	/**
	 * 根据departmentId查找部门信息及下级部门信息
	 * @param departmentId
	 * @return
	 */
	@Select("SELECT * FROM mdm_department WHERE id = #{departmentId}")
	@ResultMap("departmentMap")
	Department findAllDepartmentsById(String departmentId);
	
	/**
	 * 根据parentId查找下级部门信息
	 * @param parentId
	 * @return
	 */
	@Select("SELECT * FROM mdm_department WHERE parent_id = #{parentId}")
	@ResultMap("departmentMap")
	Department findDepartmentByParentId(String parentId);
	
	/**
     * 根据部门ID，删除部门信息
     * @param id
     */
    @Delete("DELETE FROM mdm_department WHERE id=#{id}")
    void delById(String id);
    
    @Select("SELECT * FROM mdm_department WHERE depart_code = #{departCode} OR depart_name = #{departName}")
    @ResultMap("departmentMapOnly")
    List<Department> findByCodeOrName(@Param("departCode")String departCode,@Param("departName")String departName);
}
