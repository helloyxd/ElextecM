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
	public VoResult save(BiForeignFinancing foreignFinancing){
		VoResult vor = new VoResult();
		biForeignFinancingMapper.insert(foreignFinancing);
		return vor;
	}


	@Override
	public List<BiForeignFinancing> queryAll() {
		List<BiForeignFinancing> list =  biForeignFinancingMapper.queryAll();
		return list;
	}

	@Override
	public Map<String, Object> getPage(int pageNo, int pageSize, String objName){
		Map<String, Object> map = new HashMap<String, Object>();
		int count = biForeignFinancingMapper.pageListCount(objName);
		PageQuery pageQuery = new PageQuery();
		pageQuery.setAllCount(count);
		pageQuery.setCurrentPage(pageNo);
		pageQuery.setPageRowSize(pageSize);
//		pageQuery.calcutePage(pageQuery);
		List<BiForeignFinancing> list = biForeignFinancingMapper.pageList(objName,pageQuery);
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
