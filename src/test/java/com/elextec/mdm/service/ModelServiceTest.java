package com.elextec.mdm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangkj
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelServiceTest {
	
	@Autowired
	private IMdmModelService mdmModelService;
	
	@Autowired
	private MdmModelMapper mdmModelMapper;
	
	@Test
	public void addModel() throws Exception {
		MdmModel model = new MdmModel();
		model.setStatus(0);
		model.setCreater("sys");
		model.setMdmModel("test1");
		mdmModelService.addModel(model);
		
	}
	
	@Test
	public void findModels() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(mdmModelService.getAll()));
		//System.out.println(mdmModelService.getAll().size());
	}
	
	@Test
	public void getBs() throws Exception {
		
		mdmModelService.getBsById("648C7F180F34DD05E05013AC06882D5A");
		
	}
	
	@Test
	public void test() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		//System.out.println(mapper.writeValueAsString(mdmModelMapper.queryByName("mdm_model", "mdm_model", "模块")));
		
	}
}
