/**
 * 
 */
package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;

/**
 * @author zhangkj
 *
 */
public interface IMdmModelService {

	VoResponse addModel(MdmModel model);
	
	VoResponse delModel(String modelId);
	
	VoResponse addBs(MdmBs bs);
	
	VoResponse delBs(String bsId);
	
	List<MdmModel> getAll();
	
	List<MdmModel> getAllInfo();
	
	List<MdmBs> getAllBs();
	
	MdmModel getById(String id);
	
	MdmBs getBsById(String id);
}
