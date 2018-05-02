package com.elextec.mdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.TaskList;

public interface TaskListMapper {

	@Insert("INSERT INTO tasklist VALUES(sys_guid(),#{flowId},#{flowType},#{dataId,jdbcType=VARCHAR},#{bsId,jdbcType=VARCHAR},#{modelId},"
			+ "#{remark,jdbcType=VARCHAR},#{currentUser},#{lastUser,jdbcType=VARCHAR},#{currentNode},#{lastNode,jdbcType=VARCHAR},#{status},#{creater},sysdate)")
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
	    @Result(property = "model", column = "model_id",
			many = @Many(select = "com.elextec.mdm.mapper.MdmModelMapper.findByIdOnly")),
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
	
	@Select("SELECT * FROM tasklist WHERE flow_id = #{flowId} order by create_time desc")
    @ResultMap("tasklistMap")
	List<TaskList> findByflowId(String flowId);
	
	@Select("SELECT flow_id,flow_type,data_id,bs_id,model_id,remark,current_user,last_user,current_node,last_node,status,creater,create_time"
			+ " FROM (SELECT ROW_NUMBER() OVER(PARTITION BY flow_id ORDER BY create_time DESC) rn, TASKLIST.* FROM TASKLIST) WHERE rn = 1")
    @ResultMap("tasklistMap")
	List<TaskList> findByCurrentUser(String currentUser);
	
	@Select("SELECT * FROM tasklist WHERE last_user = #{lastUser} order by create_time desc")
    @ResultMap("tasklistMap")
	List<TaskList> findByLastUser(String lastUser);
}
