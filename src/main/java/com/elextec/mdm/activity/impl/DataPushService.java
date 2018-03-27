package com.elextec.mdm.activity.impl;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

public class DataPushService implements JavaDelegate {
	
	private Expression text1;
	
	@Override
	public void execute(DelegateExecution execution) {
		// TODO Auto-generated method stub
		String value1 = (String) text1.getValue(execution);
		execution.setVariable("var1", new StringBuffer(value1).reverse().toString());
		System.out.println(value1);
	}

}
