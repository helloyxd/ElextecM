package com.elextec.mdm.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.entity.DataPermission;
import com.elextec.mdm.mapper.DataPermissionMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangkj
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataPermissionMapperTest {

	@Autowired
	private DataPermissionMapper dataPermissionMapper;
	
	@Test
    public void findDatasByUserIdAndTable() throws Exception {
		List<DataPermission> list = 
				dataPermissionMapper.findDatasByUserIdAndTable("798D5F70C6434815A1A3194C48695EC4", "83566DF9FA424C34BADEB64FE86BD7FE");
		ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(list));
		
	}
}
