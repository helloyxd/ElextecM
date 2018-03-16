package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.TaskDataRecordDetail;

public interface TaskDataRecordDetailMapper {
	@Insert("INSERT INTO taskdatarecord_detail VALUES(sys_guid(),#{flowId},#{dataId},#{modelId},#{systemId},#{taskType},"
			+ "#{remark},#{endTime},#{status},#{creater},sysdate)")
	void insert(TaskDataRecordDetail entity);
	
	@Delete("DELETE FROM taskdatarecord_detail WHERE id = #{id}")
	void del(String id);
	
	@Update("UPDATE taskdatarecord_detail SET current_user=#{currentUser} WHERE id =#{id}")
	void update(TaskDataRecordDetail entity);
	
	@Select("SELECT * FROM taskdatarecord_detail")
    @Results(id = "taskDataRecordDetailMapOnly",
    	value = {
		@Result(id = true, property = "id", column = "id"),
	    @Result(property = "flowId", column = "flow_id"),
	    @Result(property = "dataId", column = "data_id"),
	    @Result(property = "modelId", column = "model_id"),
	    @Result(property = "systemId", column = "system_id"),
	    @Result(property = "taskType", column = "task_type"),
	    @Result(property = "remark", column = "remark"),
	    @Result(property = "endTime", column = "end_time"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "creater", column = "creater"),
	    @Result(property = "createTime", column = "create_time")
	})
	List<TaskDataRecordDetail> findAll();
	
	@Select("SELECT * FROM taskdatarecord_detail")
    @Results(id = "taskDataRecordDetailMap",
		value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "flowId", column = "flow_id"),
	    @Result(property = "dataId", column = "data_id"),
	    @Result(property = "modelId", column = "model_id"),
	    @Result(property = "systemId", column = "system_id"),
	    @Result(property = "taskType", column = "task_type"),
	    @Result(property = "remark", column = "remark"),
	    @Result(property = "endTime", column = "end_time"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "creater", column = "creater"),
	    @Result(property = "createTime", column = "create_time")
	})
	TaskDataRecordDetail findById(String id);
	
	@Select("SELECT * FROM taskdatarecord_detail WHERE flow_id = #{flowId}")
    @ResultMap("taskDataRecordDetailMap")
	List<TaskDataRecordDetail> findByflowId(String flowId);
}
