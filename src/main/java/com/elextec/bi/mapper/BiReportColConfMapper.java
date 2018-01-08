package com.elextec.bi.mapper;

import com.elextec.bi.entity.BiOrgStages;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BiReportColConfMapper {

    @Select("SELECT \"org_id\",\"org_name\" FROM bi_org_stages GROUP BY \"org_id\",\"org_name\"")
    @Results(id = "biReportOrgStagesMap", value = {
	    @Result(property = "orgId", column = "org_id"),
	    @Result(property = "orgName", column = "org_name")
    })
    List<BiOrgStages> queryAllOrg();

    @Select("SELECT \"region_com_name\" FROM bi_org_stages where \"org_id\" = #{orgId} GROUP BY \"region_com_name\"")
    @Results(id = "biReportOrgStagesOnly", value = {
	    @Result(property = "regionComName", column = "region_com_name") })
    List<BiOrgStages> queryByOrgId(String orgId);
}
