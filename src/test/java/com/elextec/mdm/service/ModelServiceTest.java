package com.elextec.mdm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.entity.MdmModel;
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
}
