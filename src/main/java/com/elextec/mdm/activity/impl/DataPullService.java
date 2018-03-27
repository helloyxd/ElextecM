package com.elextec.mdm.activity.impl;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

public class DataPullService implements JavaDelegate {
	private Expression text2;
	@Override
	public void execute(DelegateExecution execution) {
		// TODO Auto-generated method stub
		String value1 = (String) text2.getValue(execution);
		System.out.println(value1);
	}

}
