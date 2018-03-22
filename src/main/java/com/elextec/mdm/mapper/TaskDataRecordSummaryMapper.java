package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.elextec.mdm.entity.TaskDataRecordSummary;

public interface TaskDataRecordSummaryMapper {
	@Insert("INSERT INTO taskdatarecord_summary VALUES(#{id},#{flowId,jdbcType=VARCHAR},#{modelId},#{taskType},#{successNum},#{failNum},"
			+ "#{remark,jdbcType=VARCHAR},#{status},#{creater},sysdate)")
	@SelectKey(before = true, keyProperty = "id",
		resultType = String.class, statementType = StatementType.STATEMENT,
		statement="SELECT sys_guid() FROM dual")
	void insert(TaskDataRecordSummary entity);
	
	@InsertProvider(type = MapperProvider.class, method = "addTaskDataRecordDetails")
    void addSummaryDetails(@Param("summary")TaskDataRecordSummary summary) ;
	
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
	    @Result(property = "model", column = "model_id",
			many = @Many(select = "com.elextec.mdm.mapper.MdmModelMapper.findByIdOnly") ),
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
	    @Result(property = "model", column = "model_id",
    		many = @Many(select = "com.elextec.mdm.mapper.MdmModelMapper.findByIdOnly") ),
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
