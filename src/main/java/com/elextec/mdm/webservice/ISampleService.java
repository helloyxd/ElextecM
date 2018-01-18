package com.elextec.mdm.webservice;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.elextec.mdm.entity.User;

@WebService
public interface ISampleService {
	//@WebMethod
   // @WebResult(name = "address")//返回值的名称为address  
	User createMpc(/*@WebParam*/ User user);//定义了一个名称为name的String类型的参数  
}
