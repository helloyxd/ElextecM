package com.elextec.mdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.elextec.mdm.common.entity.PageQuery;
import com.elextec.mdm.entity.ServiceInterfaceDefined;

public interface ServiceInterfaceDefinedMapper extends BaseMapper<ServiceInterfaceDefined>{

	@Insert("INSERT INTO mdm_serviceInterface_defined(id,type,wsdl_location,dburl,remark,user_name,password,"
			+ "model_id,bs_id,operation_type,operation,wsbinding,bing_namespace,operation_namespace,isData_source,status,creater,create_time)"
		    + " VALUES(#{id}, #{type}, #{wsdlLocation,jdbcType=VARCHAR}, #{dburl,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR},#{username}, #{password},"
		    + "#{modelId,jdbcType=VARCHAR}, #{bsId,jdbcType=VARCHAR},#{operationType,jdbcType=VARCHAR},#{operation,jdbcType=VARCHAR}, #{wsbinding,jdbcType=VARCHAR},"
		    + "#{bingNamespace,jdbcType=VARCHAR}, #{operationNamespace,jdbcType=VARCHAR}, #{isDataSource}, #{status}, #{creater}, sysdate)")
    @SelectKey(before = true, keyProperty = "id",
		resultType = String.class, statementType = StatementType.STATEMENT,
		statement="SELECT sys_guid() FROM dual")
    void insert(ServiceInterfaceDefined entity);
	
	@Update("UPDATE mdm_serviceInterface_defined SET type=#{type},wsdl_location=#{wsdlLocation},dburl=#{dburl},type=#{type},"
			+ "remark=#{remark},user_name=#{username},password=#{password},model_id=#{modelId},bs_id=#{bsId},operation_type=#{operationType},"
			+ "operation=#{operation},wsbinding=#{wsbinding},bing_namespace=#{bingNamespace},operation_namespace=#{operationNamespace},"
			+ "isData_source=#{isDataSource},status=#{status} WHERE id =#{id}")
	void update(ServiceInterfaceDefined entity);
	
	@Delete("DELETE FROM mdm_serviceInterface_defined WHERE id = #{id}")
	void del(String id);
	
	
	@Select("SELECT * FROM mdm_serviceInterface_defined")
    @Results(id = "serviceInterfaceDefinedMapOnly",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "type", column = "type"),
		    @Result(property = "wsdlLocation", column = "wsdl_location"),
		    @Result(property = "dburl", column = "dburl"),
		    @Result(property = "remark", column = "remark"),
		    @Result(property = "username", column = "user_name"),
		    @Result(property = "password", column = "password"),
		    @Result(property = "modelId", column = "model_id"),
		    @Result(property = "model", column = "model_id",
	    		many = @Many(select = "com.elextec.mdm.mapper.MdmModelMapper.findByIdOnly")),
		    @Result(property = "bsId", column = "bs_id"),
		    @Result(property = "bs", column = "bs_id",
    			many = @Many(select = "com.elextec.mdm.mapper.MdmBsMapper.findBsNameById")),
		    @Result(property = "operationType", column = "operation_type"),
		    @Result(property = "operation", column = "operation"),
		    @Result(property = "wsbinding", column = "wsbinding"),
		    @Result(property = "bingNamespace", column = "bing_namespace"),
		    @Result(property = "operationNamespace", column = "operation_namespace"),
		    @Result(property = "isDataSource", column = "isData_source"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	List<ServiceInterfaceDefined> findAll();
	
	@Select("SELECT * FROM mdm_serviceInterface_defined where model_id = #{modelId}")
	@ResultMap("serviceInterfaceDefinedMapOnly")
	List<ServiceInterfaceDefined> findByModelId(String modelId);
	
	@Select("SELECT * FROM mdm_serviceInterface_defined where model_id = #{modelId} AND isData_source = #{isDataSource}")
	@ResultMap("serviceInterfaceDefinedMapOnly")
	List<ServiceInterfaceDefined> findByModelId(String modelId, int isDataSource);
	
	@Select("SELECT * FROM mdm_serviceInterface_defined where bs_id = #{bsId}")
	@ResultMap("serviceInterfaceDefinedMap")
	List<ServiceInterfaceDefined> findByBsId(String bsId);
	
	@Select("SELECT * FROM mdm_serviceInterface_defined WHERE id = #{id}")
    @Results(id = "serviceInterfaceDefinedMap",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "type", column = "type"),
		    @Result(property = "wsdlLocation", column = "wsdl_location"),
		    @Result(property = "dburl", column = "dburl"),
		    @Result(property = "remark", column = "remark"),
		    @Result(property = "username", column = "user_name"),
		    @Result(property = "password", column = "password"),
		    @Result(property = "modelId", column = "model_id"),
		    @Result(property = "bsId", column = "bs_id"),
		    @Result(property = "operationType", column = "operation_type"),
		    @Result(property = "operation", column = "operation"),
		    @Result(property = "wsbinding", column = "wsbinding"),
		    @Result(property = "bingNamespace", column = "bing_namespace"),
		    @Result(property = "operationNamespace", column = "operation_namespace"),
		    @Result(property = "siParams", column = "id",
    			many = @Many(select = "com.elextec.mdm.mapper.ServiceInterfaceParamMapper.findBySIDefinedId") ),
		    @Result(property = "isDataSource", column = "isData_source"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	ServiceInterfaceDefined findById(String id);
	
	@Select("SELECT * FROM mdm_serviceInterface_defined where model_id = #{modelId} AND bs_id = #{bsId}")
	@ResultMap("serviceInterfaceDefinedMap")
	ServiceInterfaceDefined findByModelIdAndBsId(@Param("modelId")String modelId, @Param("bsId")String bsId);
	
	@SelectProvider(type = MapperProvider.class, method = "findEntityByPage")
	@ResultMap("serviceInterfaceDefinedMapOnly")
    List<ServiceInterfaceDefined> findByPage(@Param("queryParam") Map<String,String> map, @Param("page") PageQuery pageQuery);
	
}
