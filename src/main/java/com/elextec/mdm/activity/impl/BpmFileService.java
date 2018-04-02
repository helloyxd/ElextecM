package com.elextec.mdm.activity.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.elextec.mdm.entity.ModelFlow;

@Service
public class BpmFileService implements IBpmFileService{
	
	@Autowired
	ProcessEngine engine;
	
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
		
		return voResponse;
		
	}
	
	public VoResponse deleteBpm(String processId) {
		VoResponse voResponse = new VoResponse();
		voResponse.setSuccess(true);
		voResponse.setCode(1);
		RepositoryService repositoryService = engine
  	            .getRepositoryService();
		repositoryService.deleteDeployment(processId);
		return voResponse;
		
	}
	
	
	
	
}
