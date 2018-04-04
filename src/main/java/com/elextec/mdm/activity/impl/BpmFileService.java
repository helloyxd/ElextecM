package com.elextec.mdm.activity.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.activity.IBpmFileService;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.StatusEnum;
import com.elextec.mdm.entity.ModelFlow;
import com.elextec.mdm.service.IModelFlowService;

@Service
public class BpmFileService implements IBpmFileService{
	
	@Autowired
	ProcessEngine engine;
	
	@Autowired
	IModelFlowService modelFlowServie;
	
	@Override
	public VoResponse download(Map<String, String> param) {
		// TODO Auto-generated method stub
		
		VoResponse voResponse = new VoResponse();
		voResponse.setSuccess(true);
		voResponse.setCode(1);
		BufferedInputStream bufferedInputStream = null;
        FileOutputStream fout = null;
        try{
          
           
        	String urlString = param.get("url");
            String cooki = param.get("cooki");
            String fileName = param.get("bpmName");
            String processId = param.get("processId");
            //System.out.println();
            
            //URL url = new URL("http://localhost:8080/activiti-app/app/rest/models/8cab7989-fee2-4e81-97b5-311855c4a926/bpmn20");
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Cookie", cooki); 
            connection.getInputStream();
            
            bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            
            String realPath = this.getClass().getResource("/")+"processes/"+fileName+".bpmn20.xml";
            File file = new File(realPath.substring(0,realPath.lastIndexOf("/")));
            if(!file.exists()){
            	file.mkdirs();
            }
            fout=new FileOutputStream(realPath);
            byte[]  bytes = new byte[1024];
            
            while(bufferedInputStream.read(bytes)!=-1){
            	fout.write(bytes);
            	fout.flush();
            }
            
            fout.flush();
            
            RepositoryService repositoryService = engine
    	            .getRepositoryService();
    	    File bpmfile = new File(realPath);
    	    FileInputStream fis;
    		try {
    			fis = new FileInputStream(bpmfile);
    			repositoryService.createDeployment()
    		            .addInputStream(fileName+".bpmn20.xml", fis).deploy();
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
    		//modelFlow.setStatus(0);
    		ModelFlow  modelFlow = modelFlowServie.getModelFlowByActivitiId(processId);
    		modelFlow.setStatus(StatusEnum.StatusEnable);
    		modelFlowServie.addOrUpdate(modelFlow);
            
        }       
        catch(Exception e)
        {
        	e.printStackTrace();
        	voResponse.setCode(-1);
        	voResponse.setSuccess(false);
        	return voResponse;
        }finally {
			if(bufferedInputStream!=null){
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(fout != null){
				try {
					fout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return voResponse;
	}
	
	public VoResponse saveBpm(Map<String, String> param) {
		VoResponse voResponse = new VoResponse();
		voResponse.setSuccess(true);
		voResponse.setCode(1);
		ModelFlow modelFlow = new ModelFlow();
		modelFlow.setStatus(1);
		String processId = param.get("processId");
		String modelId = param.get("modelId");
		String operateType = param.get("operateType");
		modelFlow.setModelId(modelId);
		modelFlow.setOperationType(operateType);
		modelFlow.setActivitiId(processId);
		modelFlowServie.addOrUpdate(modelFlow);
		return voResponse;
		
	}
	
	public VoResponse deleteBpm(String processId) {
		VoResponse voResponse = new VoResponse();
		voResponse.setSuccess(true);
		voResponse.setCode(1);
		RepositoryService repositoryService = engine
  	            .getRepositoryService();
		repositoryService.deleteDeployment(processId);
		modelFlowServie.del(processId);
		return voResponse;
		
	}
	
	public VoResponse findByModel(String modelId) {
		return modelFlowServie.getByModelId(modelId);
	}
	
	public static void main(String[] arg0) {
		
		try {
			 URL url = new URL("http://localhost:8080/activiti-app/editor/index.html#/processes/e5db86e4-cce3-49c1-aabe-e8c0be68b86f/bpmn20");
			//URL url = new URL("http://localhost:8080/activiti-app/app/authentication"); 
			URLConnection connection = url.openConnection();
			 connection.setDoInput(true);  
			 connection.setDoOutput(true);  
			 OutputStreamWriter out = new OutputStreamWriter(connection  
					    .getOutputStream(), "GBK");  
					//传入数据  
					out.write("j_username=admin&j_password=test");   
					out.flush();  
					//注意记得关闭流，不然连接不能结束会抛出异常  
					out.close();
					String cookieVal = connection.getHeaderField("Set-Cookie");
				System.out.println(cookieVal);
					
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         
	}
	
}
