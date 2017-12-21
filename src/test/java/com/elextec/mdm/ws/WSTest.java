/**
 * 
 */
package com.elextec.mdm.ws;

import javax.xml.ws.Endpoint;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.elextec.mdm.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangkj
 *
 */
public class WSTest {

	public static void main(String args[]) throws Exception{

		JaxWsDynamicClientFactory dcf =JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:8080/mdm/ws/mdm/ws?wsdl");
		//Object[] objects=client.invoke("getById","798D5F70C6434815A1A3194C48695EC4");
		User user = new User();
		user.setUserName("");
		user.setUserPassword("");
		
		
		
		Object[] objects=client.invoke("add",user);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(objects));
	}
}
