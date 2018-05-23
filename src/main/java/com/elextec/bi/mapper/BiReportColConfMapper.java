package com.elextec.bi.mapper;

import com.elextec.bi.entity.BiOrgStages;
import com.elextec.bi.entity.BiReportColConf;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BiReportColConfMapper {

    @Select("SELECT TITLE,COL_CONF FROM BI_REPORT_COL_CONF WHERE REPORT_NAME = #{reportName}")
    @Results(id = "biReportColConfMap", value = {
	    @Result(property = "title", column = "TITLE"),
	    @Result(property = "colConf", column = "COL_CONF")
    })
    BiReportColConf queryByReportName(String reportName);

}
