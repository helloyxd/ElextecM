package com.elextec.bi.controller;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiOrgStages;
import com.elextec.bi.service.impl.BiReportOrgStagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集团-分期列表获取
 * Created by js_gg on 2017/12/20.
 */
@RestController
@RequestMapping(value ="/bi/orgStages")
public class BiOrgStagesController {


    @Autowired
    private BiReportOrgStagesService biReportOrgStagesService;


    /**
     * 获取所有集团接口
     * */
    @RequestMapping(value ="/queryAllOrg",method = RequestMethod.POST)
    public Object queryAllOrg(){
        //获取所有集团信息
        List<BiOrgStages> list = biReportOrgStagesService.queryAllOrg();
        List<Map<String,Object>> returnMap = new ArrayList<Map<String, Object>>();
        //将信息重新封装成map
        for(int i=0;i<list.size();i++){
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("orgId",list.get(i).getOrgId());
            params.put("orgName",list.get(i).getOrgName());
            returnMap.add(params);
        }
        VoResponse voResponse = new VoResponse();
        voResponse.setData(returnMap);
        return voResponse;
    }

    /**
     * 获取所属集团下所有区域公司
     * */
    @RequestMapping(value ="/queryByOrgId",method = RequestMethod.POST)
    public Object queryByOrgId(String id){
        //根据集团ID获取区域公司信息
        List<BiOrgStages> list = biReportOrgStagesService.queryByOrgId(id);
        List<String> returnMap = new ArrayList<String>();
        //将信息重新封装成map
        for(int i=0;i<list.size();i++){
            returnMap.add(list.get(i).getRegionComName());
        }
        VoResponse voResponse = new VoResponse();
        voResponse.setData(returnMap);
        return voResponse;
    }

}
