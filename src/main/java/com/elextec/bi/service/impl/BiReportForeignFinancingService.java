package com.elextec.bi.service.impl;

import com.elextec.bi.common.entity.PageQuery;
import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.common.entity.VoResult;
import com.elextec.bi.common.entity.constant.UserStatusEnum;
import com.elextec.bi.entity.BiForeignFinancing;
import com.elextec.bi.entity.BiUser;
import com.elextec.bi.mapper.BiForeignFinancingMapper;
import com.elextec.bi.service.BiBaseService;
import com.elextec.bi.service.IBiReportForeignFinancingService;
import com.mysql.cj.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Primary
@Service
public class BiReportForeignFinancingService extends BiBaseService implements IBiReportForeignFinancingService{

	@Autowired
	private BiForeignFinancingMapper biForeignFinancingMapper;
	
	@Override
	public VoResponse save(BiForeignFinancing foreignFinancing){
		VoResponse vor = new VoResponse();
		biForeignFinancingMapper.insert(foreignFinancing);
		return vor;
	}


	@Override
	public List<BiForeignFinancing> queryAll() {
		BiUser user = (BiUser) getSession().getAttribute("bi_user");
		List<Map<String,Object>> dataMap = user.getDataPermissions().get("2");
		if(dataMap == null){
			return null;
		}else{
			StringBuffer sql = new StringBuffer();
			sql.append("select j.\"ID\",\n" +
					"       j.ORG_ID,\n" +
					"       (case when j.\"TYPE\" = '0' then '金融机构' " +
					"             when j.\"TYPE\" = '1' then '非金融机构' " +
					"             when j.\"TYPE\" = '2' then '外部借款' " +
					"             when j.\"TYPE\" = '3' then '员工' " +
					"        end) TYPE,\n" +
					"       j.REGION_COM_NAME,\n" +
					"       j.BANK_COM_NAME,\n" +
					"       j.LOAN_AMOUNT,\n" +
					"       j.LOAN_DATE,\n" +
					"       j.APPOINTMENT_REPAYMENT_DATE,\n" +
					"       j.YEAR_COMPREHENSIVE_COST,\n" +
					"       j.COST_PAY_TYPE,\n" +
					"       j.INTEREST_PAY_TIME,\n" +
					"       j.PRESS_INFO,\n" +
					"       j.CREDIT_TERM,\n" +
					"       j.PRESS_TERM,\n" +
					"       j.\"REMARK\",\n" +
					"       j.CREATE_TIME,o.\"org_name\" AS ORG_NAME FROM BI_FOREIGN_FINANCING j LEFT JOIN (select \"org_id\",\"org_name\" FROM BI_ORG_STAGES GROUP BY \"org_id\",\"org_name\") o ON j.ORG_ID = o.\"org_id\" where 1=1");
			sql.append(" AND ");
			for(int i=0;i<dataMap.size();i++){
				if(i!=0){
					sql.append(" OR ");
				}
				sql.append("(j.ORG_ID = '"+ dataMap.get(i).get("orgId").toString()+"'");
				sql.append(" AND j.REGION_COM_NAME = '"+dataMap.get(i).get("comName").toString()+"')");
			}
			sql.append(" ORDER BY j.ORG_ID,j.\"TYPE\",j.REGION_COM_NAME");
			List<BiForeignFinancing> list =  biForeignFinancingMapper.queryAll(sql.toString());
			return list;
		}
	}

	@Override
	public Map<String, Object> getPage(int pageNo, int pageSize, String objName){
		BiUser user = (BiUser) getSession().getAttribute("bi_user");
		List<Map<String,Object>> dataMap = user.getDataPermissions().get("2");
		StringBuffer countSql = new StringBuffer();
		StringBuffer dataSql = new StringBuffer();
		if(dataMap == null){
			return null;
		}else{
			StringBuffer dataMapSqlForCount = new StringBuffer();
			StringBuffer dataMapSql = new StringBuffer();
			dataMapSqlForCount.append(" AND ");
			dataMapSql.append(" AND ");
			for(int i=0;i<dataMap.size();i++){
				if(i!=0){
					dataMapSqlForCount.append(" OR ");
					dataMapSql.append(" OR ");
				}
				dataMapSqlForCount.append("(o.\"org_id\" = '"+ dataMap.get(i).get("orgId").toString()+"'");
				dataMapSql.append("(org_id = '"+ dataMap.get(i).get("orgId").toString()+"'");
				dataMapSqlForCount.append(" AND u.region_com_name = '"+dataMap.get(i).get("comName").toString()+"')");
				dataMapSql.append(" AND region_com_name = '"+dataMap.get(i).get("comName").toString()+"')");
			}
			countSql.append("SELECT count(1)\n" +
					"        FROM BI_FOREIGN_FINANCING u\n" +
					"        LEFT JOIN (select \"org_id\",\"org_name\" FROM BI_ORG_STAGES GROUP BY \"org_id\",\"org_name\") o ON u.ORG_ID = o.\"org_id\" where ");
			if(objName != null && !StringUtils.isNullOrEmpty(objName)){
				countSql.append(" (o.\"org_name\" like '%");
				countSql.append(objName);
				countSql.append("%' OR u.region_com_name like '%");
				countSql.append(objName);
				countSql.append("%')");
			}else{
				countSql.append(" 1=1 ");
			}
			countSql.append(dataMapSqlForCount.toString());
			dataSql.append("SELECT * FROM (\n" +
					"        SELECT u.*,ROWNUM RN\n" +
					"        FROM (select j.*,o.\"org_name\" AS ORG_NAME\n" +
					"        FROM BI_FOREIGN_FINANCING j\n" +
					"        LEFT JOIN (select \"org_id\",\"org_name\" FROM BI_ORG_STAGES\n" +
					"        GROUP BY \"org_id\",\"org_name\") o ON j.ORG_ID = o.\"org_id\") u WHERE ROWNUM <= ");
			dataSql.append(pageSize*pageNo);
			dataSql.append(" ORDER BY u.create_time DESC)");
			dataSql.append(" where RN >= ");
			dataSql.append((pageNo-1)*pageSize+1);
			if(objName != null && !StringUtils.isNullOrEmpty(objName)){
				dataSql.append(" AND (org_name like '%");
				dataSql.append(objName);
				dataSql.append("%' OR region_com_name like '%");
				dataSql.append(objName);
				dataSql.append("%')");
			}
			dataSql.append(dataMapSql.toString());
			dataSql.append(" ORDER BY create_time DESC");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int count = biForeignFinancingMapper.pageListCount(countSql.toString());
//		PageQuery pageQuery = new PageQuery();
//		pageQuery.setAllCount(count);
//		pageQuery.setCurrentPage(pageNo);
//		pageQuery.setPageRowSize(pageSize);
		List<BiForeignFinancing> list = biForeignFinancingMapper.pageList(dataSql.toString());
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	@Override
	public VoResponse delete(String id){
		VoResponse voRes = new VoResponse();
		biForeignFinancingMapper.delete(id);
		return voRes;
	}
	
	@Override
	public VoResponse updateInfo(BiForeignFinancing foreignFinancing){
		VoResponse voRes = new VoResponse();
		biForeignFinancingMapper.update(foreignFinancing);
		return voRes;
	}

}
