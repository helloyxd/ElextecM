package com.elextec.bi.service;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.common.entity.VoResult;
import com.elextec.bi.entity.BiForeignFinancing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by js_gg on 2017/12/19.
 */
@Mapper
public interface IBiReportForeignFinancingService {

    public VoResult save(BiForeignFinancing temp);

    public List<BiForeignFinancing> queryAll();

    public VoResponse delete(String id);

    public VoResponse updateInfo(BiForeignFinancing temp);

    public Map<String, Object> getPage(int pageNo, int pageSize, String objName);

}
