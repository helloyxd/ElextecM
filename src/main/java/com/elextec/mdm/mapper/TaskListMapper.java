package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.TaskList;

public interface TaskListMapper {

	@Insert("INSERT INTO tasklist VALUES(sys_guid(),#{flowId},#{flowType},#{dataId},#{bsId},#{modelId},"
			+ "#{remark},#{currentUser},#{lastUser},#{currentNode},#{lastNode},#{status},#{creater},sysdate)")
	void insert(TaskList entity);
	
	@Delete("DELETE FROM tasklist WHERE id = #{id}")
	void del(String id);
	
	@Update("UPDATE tasklist SET current_user=#{currentUser} WHERE id =#{id}")
	void update(TaskList entity);
	
	@Select("SELECT * FROM tasklist")
    @Results(id = "tasklistMapOnly",
    	value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "flowId", column = "flow_id"),
	    @Result(property = "flowType", column = "flow_type"),
	    @Result(property = "dataId", column = "data_id"),
	    @Result(property = "bsId", column = "bs_id"),
	    @Result(property = "modelId", column = "model_id"),
	    @Result(property = "remark", column = "remark"),
	    @Result(property = "currentUser", column = "current_user"),
	    @Result(property = "lastUser", column = "last_user"),
	    @Result(property = "currentNode", column = "current_node"),
	    @Result(property = "lastNode", column = "last_node"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "creater", column = "creater"),
	    @Result(property = "createTime", column = "create_time")
	})
	List<TaskList> findAll();
	
	@Select("SELECT * FROM tasklist")
    @Results(id = "tasklistMap",
		value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "flowId", column = "flow_id"),
	    @Result(property = "flowType", column = "flow_type"),
	    @Result(property = "dataId", column = "data_id"),
	    @Result(property = "bsId", column = "bs_id"),
	    @Result(property = "modelId", column = "model_id"),
	    @Result(property = "remark", column = "remark"),
	    @Result(property = "currentUser", column = "current_user"),
	    @Result(property = "lastUser", column = "last_user"),
	    @Result(property = "currentNode", column = "current_node"),
	    @Result(property = "lastNode", column = "last_node"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "creater", column = "creater"),
	    @Result(property = "createTime", column = "create_time")
	})
	TaskList findById(String id);
	
	@Select("SELECT * FROM tasklist WHERE flow_id = #{flowId}")
    @ResultMap("tasklistMap")
	List<TaskList> findByflowId(String flowId);
}
