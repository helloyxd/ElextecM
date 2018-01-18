/**
 * 
 */
package com.elextec.mdm.ws;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;

import com.elextec.mdm.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangkj
 *
 */
public class WSTest {
	private static String namespace="http://ws.mdm.elextec.com/";
	private static String serviceName="MdmService";
	private static String service;
	private static String methodName;
	private static final QName SERVICE_NAME = new QName(namespace, serviceName);
	
	void executeWS(){
		// 远程webService的URL
        String hostUrl = "****************?wsdl";
        try {
            // 创建动态客户端
            JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
            // 创建客户端连接
            Client client = factory.createClient(hostUrl, SERVICE_NAME);
            ClientImpl clientImpl = (ClientImpl) client;
            Endpoint endpoint = clientImpl.getEndpoint();
            // Make use of CXF service model to introspect the existing WSDL
            ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
            // 创建QName来指定NameSpace和要调用的service
            QName bindingName = new QName(namespace, service);
            BindingInfo binding = serviceInfo.getBinding(bindingName);
            // 创建QName来指定NameSpace和要调用的方法
            QName opName = new QName(namespace, methodName);
             
            BindingOperationInfo boi = binding.getOperation(opName);
            BindingMessageInfo inputMessageInfo = boi.getInput();
            List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
            // 取得对象实例
            MessagePartInfo partInfo = parts.get(0);
            Class<?> partClass = partInfo.getTypeClass();
            Object inputObject = partClass.newInstance();
 
            // 取得字段的set方法并赋值
            PropertyDescriptor partPropertyDescriptor = new PropertyDescriptor("字段名", partClass);
            Method userNoSetter = partPropertyDescriptor.getWriteMethod();
            userNoSetter.invoke(inputObject, "属性值");
 
            // 取得字段的set方法并赋值
            PropertyDescriptor partPropertyDescriptor2 = new PropertyDescriptor("字段名", partClass);
            Method productCodeSetter = partPropertyDescriptor2.getWriteMethod();
            productCodeSetter.invoke(inputObject, "属性值");
 
            // 调用客户端invoke()方法，把inputObject传递给要调用的方法并取得结果对象
            Object[] result = client.invoke(opName, inputObject);
            // 取得的结果是一个对象
            Class<?> resultClass = result[0].getClass();
            // 取得返回结果的get方法并得到它的值
            PropertyDescriptor resultDescriptor = new PropertyDescriptor("结果字段名", resultClass);
            Object resultGetter = resultDescriptor.getReadMethod().invoke(result[0]);
            System.out.println("result：" + resultGetter);
            // 取得返回结果的get方法并得到它的值
            PropertyDescriptor tokenDescriptor = new PropertyDescriptor("结果字段名", resultClass);
            // 取得的是一个对象实例
            Object getObj= tokenDescriptor.getReadMethod().invoke(result[0]);
            if(getObj != null) {
                Class<?> resultTokenClass = tokenDescriptor.getReadMethod().invoke(result[0]).getClass();
                // 得到对象实例下的***属性值
                PropertyDescriptor expiredTimeDescriptor = new PropertyDescriptor("字段名", resultTokenClass);
                Object getter = expiredTimeDescriptor.getReadMethod().invoke(getObj);
                System.out.println("字段名：" + getter );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	void executeWS2() throws Exception{
		JaxWsDynamicClientFactory dcf =JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:8080/mdm/ws/user?wsdl");
		Object[] objects=client.invoke("getById","798D5F70C6434815A1A3194C48695EC4");
		
		System.out.println(objects);
	}
	
	void executeWS3() throws Exception{
		JaxWsDynamicClientFactory dcf =JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:8080/mdm/ws/user?wsdl");
		Object user = Thread.currentThread().getContextClassLoader().loadClass("com.elextec.mdm.ws.User").newInstance();     
		Method m = user.getClass().getMethod("setUserName", String.class);    
		m.invoke(user, "Joe Schmoe");    
		Method m1 = user.getClass().getMethod("setUserPassword", String.class);    
		m1.invoke(user, "C001"); 
		Method m2 = user.getClass().getMethod("setCreater", String.class);    
		m2.invoke(user, "C001");    
		  
		Object[] objects = client.invoke("add", user);  
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(objects));
	}
	
	

	public static void main(String args[]) throws Exception{
		WSTest test = new WSTest();
		test.executeWS2();
	}
}
