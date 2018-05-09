package com.elextec.mdm.webservice;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.elextec.mdm.common.entity.VoResponse;

@WebService(name="IMdmWebService")
public interface IMdmService {
	
	@WebMethod
	public List<String> getModels();
	
	@WebMethod
	public List<Object> getTables(String model);
	
	@WebMethod
    public String excute(@WebParam(name="param") String s);
	
	@WebMethod
	public VoResponse postDefinedData(String model, Map<String,String> map);
	
}
