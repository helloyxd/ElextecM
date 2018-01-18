package com.elextec.mdm.provider;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;

import org.springframework.stereotype.Service;

import com.elextec.mdm.webservice.impl.SampleService;

@Service
public class WebServiceProvider {
	@PostConstruct
	public void provideWebService() {
		//Endpoint.publish("http://localhost:8080/mpc", new SampleService());  
	}
}
