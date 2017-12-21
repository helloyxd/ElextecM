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
	
	VoResponse addBs(MdmBs bs);
	
	List<MdmModel> getAll();
}
