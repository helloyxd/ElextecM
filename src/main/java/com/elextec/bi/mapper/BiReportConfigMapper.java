package com.elextec.bi.mapper;

import com.elextec.bi.entity.BiReportConfig;
import com.elextec.bi.entity.BiRole;
import com.elextec.mdm.mapper.MapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BiReportConfigMapper {

    @Select("SELECT * FROM BI_REPORT_CONFIG WHERE id = #{id}")
    @Results(id = "biReportConfigMap", value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "reportName", column = "REPORT_NAME"),
	    @Result(property = "colConf", column = "COL_CONF"),
        @Result(property = "datasetConfig", column = "DATASET_CONFIG"),
        @Result(property = "tableHeadConfig", column = "TABLE_HEAD_CONFIG"),
        @Result(property = "screenParameterConfig", column = "SCREEN_PARAMETER_CONFIG"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater"),
    })
    BiReportConfig findById(String id);

    @Select("SELECT * FROM BI_REPORT_CONFIG")
    @ResultMap("biReportConfigMap")
    List<BiReportConfig> findAll();

    @Insert("INSERT INTO BI_REPORT_CONFIG(id,REPORT_NAME,COL_CONF,DATASET_CONFIG,TABLE_HEAD_CONFIG,SCREEN_PARAMETER_CONFIG,status,creater,create_time)"
            + " VALUES(sys_guid(), #{reportName}, #{colConf},#{datasetConfig},#{tableHeadConfig},#{screenParameterConfig}, #{status}, #{creater}, sysdate)")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(BiReportConfig biReportConfig);

    @Update("UPDATE BI_REPORT_CONFIG SET REPORT_NAME=#{reportName},COL_CONF=#{colConf},DATASET_CONFIG=#{datasetConfig},TABLE_HEAD_CONFIG=#{tableHeadConfig},SCREEN_PARAMETER_CONFIG=#{screenParameterConfig} WHERE id =#{id}")
    void update(BiReportConfig biReportConfig);

    @Delete("DELETE FROM BI_REPORT_CONFIG WHERE id =#{id}")
    void delete(String id);

//    /**
//     * 新增业务表的数据权限
//     *
//     */
//    @InsertProvider(type = BiMapperProvider.class, method = "addReportDataPermission")
//    void addReportDataPermission(@Param("reportId")String reportId,@Param("dataPermissions")String[] dataPermissions);
//
//    /**
//     * 根据业务表ID，删除对应的数据权限
//     *
//     * @param reportId
//     */
//    @Delete("DELETE FROM BI_REPORT_DATAPERMISSION WHERE REPORT_ID=#{reportId}")
//    void delReportDataPermission(String reportId);

}
