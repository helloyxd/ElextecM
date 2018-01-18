package com.elextec.mdm.service;

import java.beans.PropertyDescriptor;
import java.util.List;

import javax.xml.namespace.QName;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiServiceTest {
	
	@Autowired
	private IActivitiService activitiService;

	@Test
	public void processTest() {
		ProcessInstance process = activitiService.startProcess();
		System.out.println(process.getId());
		System.out.println(process.getActivityId());
		List<Task>  taskList = activitiService.getTasks("");
		System.out.println(taskList.size());
		if(taskList.size()>0) {
			System.out.println(taskList.get(0).getName());
			activitiService.completeTasks(taskList.get(0).getId());
		}
		taskList = activitiService.getTasks("");
		if(taskList.size()>0) {
			System.out.println(taskList.get(0).getName());
			activitiService.completeTasks(taskList.get(0).getId());
		}
		
		
	}
	
	public static void main(String[] argo) {
		/*JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:8080/mdm/services/user?wsdl");
		Object[] objects = null;
		try {
			QName qname = new QName("http://webservice.mdm.elextec.com/", "createMpc"); 
			User user = new User();
			user.setFullName("test");
			objects = client.invoke(qname, user);
			System.out.println("返回数据:" + objects[0]);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}*/
		try {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/OrderProcess?wsdl");
        Endpoint endpoint = client.getEndpoint();


        // Make use of CXF service model to introspect the existing WSDL
        ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
        QName bindingName = new QName("http://order.demo/", "OrderProcessServiceSoapBinding");
        BindingInfo binding = serviceInfo.getBinding(bindingName);
        QName opName = new QName("http://order.demo/", "processOrder");
        BindingOperationInfo boi = binding.getOperation(opName); // Operation name is processOrder
        BindingMessageInfo inputMessageInfo = null;
        if (!boi.isUnwrapped()) {
            //OrderProcess uses document literal wrapped style.
            inputMessageInfo = boi.getWrappedOperation().getInput();
        } else {
            inputMessageInfo = boi.getUnwrappedOperation().getInput();
        }

        List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
        MessagePartInfo partInfo = parts.get(0); // Input class is Order

        // Get the input class Order
        Class<?> orderClass = partInfo.getTypeClass();
        Object orderObject=orderObject = orderClass.newInstance();
		

        // Populate the Order bean
        // Set customer ID, item ID, price and quantity
        PropertyDescriptor custProperty = new PropertyDescriptor("customerID", orderClass);
        custProperty.getWriteMethod().invoke(orderObject, "C001");
        PropertyDescriptor itemProperty = new PropertyDescriptor("itemID", orderClass);
        itemProperty.getWriteMethod().invoke(orderObject, "I001");
        PropertyDescriptor priceProperty = new PropertyDescriptor("price", orderClass);
        priceProperty.getWriteMethod().invoke(orderObject, Double.valueOf(100.00));
        PropertyDescriptor qtyProperty = new PropertyDescriptor("qty", orderClass);
        qtyProperty.getWriteMethod().invoke(orderObject, Integer.valueOf(20));

        // Invoke the processOrder() method and print the result
        // The response class is String
        Object[] result = client.invoke(opName, orderObject);
        System.out.println("The order ID is " + result[0]);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


}
