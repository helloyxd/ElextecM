package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.StatusEnum;
import com.elextec.mdm.entity.DataPermission;
import com.elextec.mdm.entity.DataPermissionDefined;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.DataPermissionDefinedMapper;
import com.elextec.mdm.mapper.DataPermissionMapper;
import com.elextec.mdm.mapper.TableDDLMapper;
import com.elextec.mdm.mapper.TableDefinitionMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IDataPermissionService;

/**
 * @author zhangkj
 *
 */
@Service
public class DataPermissionService extends BaseService implements IDataPermissionService {

	@Autowired
	private DataPermissionMapper dataPermissionMapper;
	
	@Autowired
	private DataPermissionDefinedMapper dataPermissionDefinedMapper;
	
	@Autowired
	private TableDDLMapper tableDDLMapper;
	
	@Autowired
	private TableDefinitionMapper tableDefinitionMapper;

	@Override
	public VoResponse addDataPermissionDefined(DataPermissionDefined entity) {
		VoResponse voRes = new VoResponse();
		TableDefinition table = tableDefinitionMapper.findById(entity.getTableId());
		if(table == null){
			voRes.setNull(voRes);
			voRes.setMessage("自定义表信息不存在");
			return voRes;
		}
		if(tableDDLMapper.queryColumnName(table.getTableName().toUpperCase(), entity.getPermissionField().toUpperCase()) == 0){
			voRes.setNull(voRes);
			voRes.setMessage("自定义表字段名 " + entity.getPermissionField() + " 不存在");
			return voRes;
		}
		List<DataPermission> list = entity.getDataPermission();
		if(list == null || list.size() == 0){
			voRes.setNull(voRes);
			voRes.setMessage("数据权限信息不能为空");
			return voRes;
		}
		String userName = getUserName();
		for(DataPermission e : list){
			if(e.getPermissionValue() == null || e.getPermissionValue().equals("")){
				voRes.setNull(voRes);
				voRes.setMessage("数据权限值不能为空");
				return voRes;
			}
			e.setCreater(userName);
		}
		DataPermissionDefined newEntity = null;
		//add
		if(entity.getId() == null || entity.getId().equals("")){
			DataPermissionDefined oldEntity = dataPermissionDefinedMapper.findByName(entity.getTableId(), entity.getPermissionField());
			if(oldEntity != null){//数据权限定义表字段已经存在,判断值
				List<DataPermission> listdp = oldEntity.getDataPermission();
				Iterator<DataPermission> it = entity.getDataPermission().iterator();
				while(it.hasNext()){
					DataPermission dp = it.next();
					dp.setCreater(userName);
					for(DataPermission olddp : listdp){
						if(olddp.getRoleId().equals(dp.getRoleId()) && olddp.getPermissionValue().equals(dp.getPermissionValue())){
							voRes.setFail(voRes);
							voRes.setMessage("数据权限定义表已经存在！");
							return voRes;
						}
					}
				}
				entity.setId(oldEntity.getId());
			}else{
				entity.setCreater(userName);
				entity.setStatus(StatusEnum.StatusEnable);
				dataPermissionDefinedMapper.insert(entity);
			}
			dataPermissionDefinedMapper.addDataPermissions(entity);
			return voRes;
		//update
		}else{
			newEntity = dataPermissionDefinedMapper.findById(entity.getId());
			if(newEntity == null){
				voRes.setNull(voRes);
				voRes.setMessage("数据权限定义表不存在");
				return voRes;
			}
			delDataPermissions(newEntity.getDataPermission());
			dataPermissionDefinedMapper.addDataPermissions(entity);
			return voRes;
		}
		
	}

	@Override
	public List<DataPermissionDefined> getAll() {
		List<DataPermissionDefined> list = dataPermissionDefinedMapper.findAll();
		return list;
	}

	@Override
	public VoResponse delDataPermission(String id) {
		VoResponse voRes = new VoResponse();
		DataPermission dataPermission = dataPermissionMapper.findById(id);
		if(dataPermission != null){
			dataPermissionMapper.del(id);
		}else{
			voRes.setNull(voRes);
		}
		return voRes;
	}

	@Override
	public VoResponse delDataPermissionDefined(String id) {
		VoResponse voRes = new VoResponse();
		DataPermissionDefined defined = dataPermissionDefinedMapper.findById(id);
		if(defined == null){
			voRes.setNull(voRes);
		}else{
			List<DataPermission> list = defined.getDataPermission();
			if(list != null && list.size() > 0){
				List<String> ids = new ArrayList<String>();
				for(DataPermission e : list){
					ids.add(e.getId());
				}
				dataPermissionMapper.delAll("mdm_datapermission", ids);
			}
			dataPermissionDefinedMapper.del(id);
		}
		return voRes;
	}
	
	public void delDataPermissions(List<DataPermission> list){
		if(list != null && list.size() > 0){
			List<String> ids = new ArrayList<String>();
			for(DataPermission e : list){
				ids.add(e.getId());
			}
			dataPermissionMapper.delAll("mdm_datapermission", ids);
		}
	}
}
