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
public interface IActivitiTestService {

	
	
	public void startProcess();
	public void getTasks(String assignee);
	public void completeTasks(String taskId);
}
