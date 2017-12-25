package com.elextec.mdm.service.impl;

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
		entity.setCreater(userName);
		entity.setStatus(StatusEnum.StatusEnable);
		dataPermissionDefinedMapper.insert(entity);
		DataPermissionDefined newEntity = dataPermissionDefinedMapper.findByName(entity.getTableId(), entity.getPermissionField());
		entity.setId(newEntity.getId());
		dataPermissionDefinedMapper.addDataPermissions(entity);
		return voRes;
	}

	@Override
	public List<DataPermissionDefined> getAll() {
		List<DataPermissionDefined> list = dataPermissionDefinedMapper.findAll();
		return list;
	}
	
	
}
