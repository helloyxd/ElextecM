package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.TaskDataRecordSummary;

public interface TaskDataRecordSummaryMapper {
	@Insert("INSERT INTO taskdatarecord_summary VALUES(sys_guid(),#{flowId},#{modelId},#{taskType},#{successNum},#{failNum},"
			+ "#{remark},#{status},#{creater},sysdate)")
	void insert(TaskDataRecordSummary entity);
	
	@Delete("DELETE FROM taskdatarecord_summary WHERE id = #{id}")
	void del(String id);
	
	@Update("UPDATE taskdatarecord_summary SET current_user=#{currentUser} WHERE id =#{id}")
	void update(TaskDataRecordSummary entity);
	
	@Select("SELECT * FROM taskdatarecord_summary")
    @Results(id = "taskDataRecordSummaryMapOnly",
    	value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "flowId", column = "flow_id"),
	    @Result(property = "modelId", column = "model_id"),
	    @Result(property = "taskType", column = "task_type"),
	    @Result(property = "successNum", column = "success_num"),
	    @Result(property = "failNum", column = "fail_num"),
	    @Result(property = "remark", column = "remark"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "creater", column = "creater"),
	    @Result(property = "createTime", column = "create_time")
	})
	List<TaskDataRecordSummary> findAll();
	
	@Select("SELECT * FROM taskdatarecord_summary")
    @Results(id = "taskDataRecordSummaryMap",
		value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "flowId", column = "flow_id"),
	    @Result(property = "modelId", column = "model_id"),
	    @Result(property = "taskType", column = "task_type"),
	    @Result(property = "successNum", column = "success_num"),
	    @Result(property = "failNum", column = "fail_num"),
	    @Result(property = "remark", column = "remark"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "creater", column = "creater"),
	    @Result(property = "createTime", column = "create_time")
	})
	TaskDataRecordSummary findById(String id);
	
	@Select("SELECT * FROM taskdatarecord_summary WHERE flow_id = #{flowId}")
    @ResultMap("taskDataRecordSummaryMap")
	List<TaskDataRecordSummary> findByflowId(String flowId);
}
