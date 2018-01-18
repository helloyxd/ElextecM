package com.elextec.bi.service.impl;
import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiDataSource;
import com.elextec.bi.mapper.BiDataSourceMapper;
import com.elextec.bi.service.BiBaseService;
import com.elextec.bi.service.IBiDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//@Primary
@Service
public class BiDataSourceService extends BiBaseService implements IBiDataSourceService{

	@Autowired
	private BiDataSourceMapper biDataSourceMapper;

	@Override
	public BiDataSource queryById(String id){
		return biDataSourceMapper.queryById(id);
	}

	@Override
	public VoResponse save(BiDataSource biDataSource){
		VoResponse voRes = new VoResponse();
		BiDataSource temp = biDataSourceMapper.queryBySourceName(biDataSource.getSourceName());
		if(temp != null){
			voRes.setFail(voRes);
			voRes.setMessage("数据源名称已存在");
		}else{
			biDataSourceMapper.insert(biDataSource);
		}
		return voRes;
	}

	@Override
	public VoResponse update(BiDataSource biDataSource){
		VoResponse voRes = new VoResponse();
		biDataSourceMapper.update(biDataSource);
		return voRes;
	}

	@Override
	public VoResponse delete(String id){
		VoResponse voRes = new VoResponse();
		biDataSourceMapper.delete(id);
		return voRes;

	}

	@Override
	public List<BiDataSource> queryAll(){
		return biDataSourceMapper.queryAll();
	}

}
