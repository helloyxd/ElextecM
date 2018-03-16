package com.elextec.mdm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Element;

import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.TableDDLMapper;
import com.elextec.mdm.utils.BeanUtil;
import com.ibm.wsdl.extensions.schema.SchemaImpl;

import java.beans.PropertyDescriptor;

import javax.wsdl.Definition;
import javax.wsdl.Types;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaAttribute;
import org.apache.ws.commons.schema.XmlSchemaAttributeOrGroupRef;
import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.apache.ws.commons.schema.XmlSchemaComplexContent;
import org.apache.ws.commons.schema.XmlSchemaComplexContentExtension;
import org.apache.ws.commons.schema.XmlSchemaComplexType;
import org.apache.ws.commons.schema.XmlSchemaContent;
import org.apache.ws.commons.schema.XmlSchemaContentModel;
import org.apache.ws.commons.schema.XmlSchemaContentType;
import org.apache.ws.commons.schema.XmlSchemaElement;
import org.apache.ws.commons.schema.XmlSchemaObject;
import org.apache.ws.commons.schema.XmlSchemaParticle;
import org.apache.ws.commons.schema.XmlSchemaSequence;
import org.apache.ws.commons.schema.XmlSchemaSequenceMember;
import org.apache.ws.commons.schema.XmlSchemaSimpleType;
import org.apache.ws.commons.schema.XmlSchemaSimpleTypeRestriction;
import org.apache.ws.commons.schema.XmlSchemaType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableMapperTest {

	@Autowired
	private TableDDLMapper tableDLLMapper;

	@Autowired
	private ITableDDLService tableDLLService;

	//@Test
	public void createTable() throws Exception {
		String sql = "";
	}

	private static Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();

	@Test
	public void createTableService() throws Exception {
		TableDefinition table = new TableDefinition();
		table.setModelId("B1536179075A492B96192DE9EDA69CEA");
		table.setTableName("MDMTest9");
		table.setTableLabel("测试");
		table.setCreater("sys");
		table.setStatus(0);
		List<ColumnDefinition> list = new ArrayList<ColumnDefinition>();
		ColumnDefinition obj = new ColumnDefinition();
		obj.setName("id");
		Map<Integer, String> dataTypeMap = new HashMap<Integer, String>();
		dataTypeMap.put(9, "");
		Map<String,String> dataTypeMap1 = new HashMap<String,String>();
		dataTypeMap1.put("VARCHAR2", "32");
		obj.setDataTypeMap(dataTypeMap1);
		List<String> constraints = new ArrayList<String>();
		constraints.add("P");
		obj.setConstraints(constraints);
		list.add(obj);
		table.setColumnDefinitions(list);
		tableDLLService.createTable(table);

	}

	public static void main(String str[]) throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		 Client client = dcf.createClient("http://localhost:8080/mdm/services/user?wsdl");
		//Client client = dcf.createClient("C:\\zjz\\DocServiceForMsg.xml");
		// Client client =
		// dcf.createClient("http://192.168.2.30:88//services/DocService?wsdl");
		Endpoint endpoint = client.getEndpoint();

		// Make use of CXF service model to introspect the existing WSDL
		ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
		//QName bindingName = new QName("http://localhost/services/DocServiceForMsg", "DocServiceForMsgHttpBinding");
		QName bindingName = new QName("http://impl.webservice.mdm.elextec.com/", "SampleServiceServiceSoapBinding");
		
		BindingInfo binding = serviceInfo.getBinding(bindingName);
		QName opName = new QName("http://webservice.mdm.elextec.com/", "createMpc");
		//QName opName = new QName("http://localhost/services/DocServiceForMsg", "updateDoc");
		BindingOperationInfo boi = binding.getOperation(opName); // Operation name is processOrder
		BindingMessageInfo inputMessageInfo = null;
		if (!boi.isUnwrapped()) {
			// OrderProcess uses document literal wrapped style.
			inputMessageInfo = boi.getWrappedOperation().getInput();
		} else {
			inputMessageInfo = boi.getUnwrappedOperation().getInput();
		}

		List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
		MessagePartInfo partInfo = parts.get(0); // Input class is Order

		// Get the input class Order
		Class<?> orderClass = partInfo.getTypeClass();
		Object orderObject = orderClass.newInstance();

		// Populate the Order bean
		// Set customer ID, item ID, price and quantity
		// PropertyDescriptor custProperty = new PropertyDescriptor("userName",
		// orderClass);
		// custProperty.getWriteMethod().invoke(orderObject, "001");
		// PropertyDescriptor itemProperty = new PropertyDescriptor("itemID",
		// orderClass);
		// itemProperty.getWriteMethod().invoke(orderObject, "I001");
		// PropertyDescriptor priceProperty = new PropertyDescriptor("price",
		// orderClass);
		// priceProperty.getWriteMethod().invoke(orderObject, Double.valueOf(100.00));
		// PropertyDescriptor qtyProperty = new PropertyDescriptor("qty", orderClass);
		// qtyProperty.getWriteMethod().invoke(orderObject, Integer.valueOf(20));

		// Invoke the processOrder() method and print the result
		// The response class is String
		BindingMessageInfo outputMessageInfo = null;
		if (!boi.isUnwrapped()) {
			// OrderProcess uses document literal wrapped style.
			outputMessageInfo = boi.getWrappedOperation().getOutput();
		} else {
			outputMessageInfo = boi.getUnwrappedOperation().getOutput();
		}

		List<MessagePartInfo> outMessageParts = inputMessageInfo.getMessageParts();
		// Map<String, Object> map1 = outMessageParts.get(0).getProperties();
		MessagePartInfo message = outMessageParts.get(0);
		System.out.println(message.getElementQName());
		String topName = message.getTypeQName().getLocalPart();
		XmlSchemaElement top = (XmlSchemaElement) (message.getXmlSchema());
		search(top.getSchemaType(),top.getSchemaType().getName());
		/*
		 * if(top.getSchemaType() instanceof XmlSchemaComplexType) {
		 * XmlSchemaComplexType xmlSchemaComplexType =
		 * (XmlSchemaComplexType)top.getSchemaType();
		 * if(xmlSchemaComplexType.getContentModel() instanceof XmlSchemaComplexContent)
		 * { XmlSchemaComplexContent complexContent = } }else if(top.getSchemaType()
		 * instanceof XmlSchemaSimpleType) { XmlSchemaSimpleType xmlSchemaSimpleType =
		 * (XmlSchemaSimpleType)top.getSchemaType(); } Object[] result =
		 * client.invoke(opName, orderObject);;
		 * System.out.println(result[0].getClass().getSimpleName()); Map<String, Object>
		 * map = new HashMap<String,Object>(); List<Map<String,Object>> list =
		 * BeanUtil.arrayToMaps(result); System.out.println(list.size());
		 * System.out.println(list.get(0).get("userName"));
		 * 
		 * System.out.println("The order ID is " + result[0]);
		 * parseWSDL("http://localhost:8080/mdm/services/user?wsdl=ISampleService.wsdl")
		 * ;
		 */
	}

	public static void parseWSDL(String wsdlURI) throws WSDLException {
		WSDLFactory wsdlFactory = WSDLFactory.newInstance();
		WSDLReader reader = wsdlFactory.newWSDLReader();
		Definition defintion = reader.readWSDL(wsdlURI);// 如图3，根据wsdlURI地址得到的definition即为最外层的标签
		processTypes(defintion);// 如图4，processTypes函数对defintion下的types标签进行进一步解析

	}

	public static void processTypes(Definition defintion) {
		XmlSchemaCollection xmlSchemaCollection = new XmlSchemaCollection();
		Types types = defintion.getTypes();// 得到definition下一级中的types
		List list = types.getExtensibilityElements();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			SchemaImpl schemaImpl = (SchemaImpl) iterator.next();
			Element element = (Element) schemaImpl.getElement();
			XmlSchema xmlSchema = xmlSchemaCollection.read(element);

			Map<QName, XmlSchemaType> smlSchemaObjectTable = xmlSchema.getSchemaTypes();
			// 如图5，得到types下的complexType
			Map<QName, XmlSchemaElement> elements = xmlSchema.getElements();
			// 如图5，得到types下的element

			Iterator elementsItr = elements.entrySet().iterator();// 对element进行进一步深入
			Iterator elementsKeyItr = elements.keySet().iterator();
			while (elementsKeyItr.hasNext()) {
				Object o = elementsKeyItr.next();
				Object c = elements.get(o);
				System.out.println(o.getClass().getName());
			}

			while (elementsKeyItr.hasNext()) {
				XmlSchemaElement elemt = (XmlSchemaElement) (((Map) elementsItr.next()).entrySet().iterator().next());
				String elemtName = elemt.getName();
				if (elemtName.equals("")) {
					XmlSchemaType elemtNameType = elemt.getSchemaType();
					search(elemtNameType,elemtNameType.getName()); // search函数用于对复杂类型进行专门的处理
				}
			}
			Iterator typesItr = smlSchemaObjectTable.entrySet().iterator();// 对complexType进行进一步深入
			while (typesItr.hasNext()) {
				XmlSchemaType type = (XmlSchemaType) typesItr.next();
				String typeName = type.getName();
				if (typeName.equals("")) {
					search(type,type.getName());
				}
			}
		}
	}

	public static void search(XmlSchemaType type,String parentName) {
		// 如果是复杂类型，则按照XmlSchemaComplexType-> XmlSchemaSequence->
		// XmlSchemaElement的层次进行解析，因为XmlSchemaElement可能任然是复杂类型，所以需要进一步判断递归调用Search。如图6，图7
		if (type instanceof XmlSchemaComplexType) {
			String tableName = "";
			if(type.getQName()==null) {//说明其是个集合类型，下面包含具体类型
				XmlSchemaParticle xmlSchemaParticle = ((XmlSchemaComplexType) type).getParticle();
				XmlSchemaSequence xmlSchemaSequence = (XmlSchemaSequence) xmlSchemaParticle;
				List<XmlSchemaSequenceMember> sequenceMembers = xmlSchemaSequence.getItems();
				XmlSchemaElement element = (XmlSchemaElement)(sequenceMembers.get(0));
				tableName = element.getName();
				search(element.getSchemaType(),tableName);
				
			}else {
				tableName = type.getQName().getLocalPart();
			}
			
			
			
			
			if (dataMap.get(tableName) == null) {
				dataMap.put(tableName, new HashMap<String, String>());
			}else {
				return;
			}
			XmlSchemaComplexType xmlSchemaComplexType = (XmlSchemaComplexType) type;
			XmlSchemaContentModel xmlSchemaContentModel = xmlSchemaComplexType.getContentModel();
			if (xmlSchemaContentModel != null && xmlSchemaContentModel instanceof XmlSchemaComplexContent) {//如果复杂类型下面不存在xmlSchemaContentModel(即complexContent元素)
				XmlSchemaComplexContent xmlSchemaComplexContent = (XmlSchemaComplexContent) xmlSchemaContentModel;
				if (xmlSchemaComplexContent != null) { // 判断是否存在complexContent
					XmlSchemaContent xmlSchemaContent = xmlSchemaComplexContent.getContent();
					XmlSchemaComplexContentExtension extension = (XmlSchemaComplexContentExtension) xmlSchemaContent;
					if (extension != null) {
						XmlSchemaParticle xmlSchemaParticle = extension.getParticle();
						XmlSchemaSequence xmlSchemaSequence = (XmlSchemaSequence) xmlSchemaParticle;
						List<XmlSchemaSequenceMember> sequenceMembers = xmlSchemaSequence.getItems();
						int count = sequenceMembers.size();
						for (int i = 0; i < count; i++) {
							if (sequenceMembers.get(i) instanceof XmlSchemaElement) {
								XmlSchemaElement xmlSchemaElement = (XmlSchemaElement) sequenceMembers.get(i);
								System.out.println(xmlSchemaElement.getQName().getLocalPart());
								String elementName = xmlSchemaElement.getName();
								if(elementName.equals("roles")) {
									System.out.println(11);
								}
								XmlSchemaType xmlSchemaType = xmlSchemaElement.getSchemaType();
								String elementTypeName = xmlSchemaType.getName();
								if(xmlSchemaType instanceof XmlSchemaSimpleType) {
									if (elementTypeName != null) {
										System.out.println("---"+elementTypeName);
									}
								}else {
									search(xmlSchemaType,elementName);
								}
							}
						}
					} else {
						System.out.println("have XmlSchemaComplexContent bu not have xmlSchemaContent or extension");
					}
				} else {
					XmlSchemaParticle xmlSchemaParticle = xmlSchemaComplexType.getParticle();
					XmlSchemaSequence xmlSchemaSequence = (XmlSchemaSequence) xmlSchemaParticle;
					List<XmlSchemaSequenceMember> sequenceMembers = xmlSchemaSequence.getItems();
					int count = sequenceMembers.size();
					for (int i = 0; i < count; i++) {
						if (sequenceMembers.get(i) instanceof XmlSchemaElement) {
							XmlSchemaElement xmlSchemaElement = (XmlSchemaElement) sequenceMembers.get(i);
							String elementName = xmlSchemaElement.getName();
							XmlSchemaType xmlSchemaType = xmlSchemaElement.getSchemaType();
							String elementTypeName = xmlSchemaType.getName();
							if (elementTypeName != null) {
								System.out.println(elementName);
							}
							search(xmlSchemaType,elementName);
						}
					}

				}

			} else { //如果复杂类型下面不存在xmlSchemaContentModel(即complexContent元素)
				XmlSchemaParticle xmlSchemaParticle = xmlSchemaComplexType.getParticle();
				XmlSchemaSequence xmlSchemaSequence = (XmlSchemaSequence) xmlSchemaParticle;
				List<XmlSchemaSequenceMember> sequenceMembers = xmlSchemaSequence.getItems();
				int count = sequenceMembers.size();
				for (int i = 0; i < count; i++) {
					if (sequenceMembers.get(i) instanceof XmlSchemaElement) {
						XmlSchemaElement xmlSchemaElement = (XmlSchemaElement) sequenceMembers.get(i);
						String elementName = xmlSchemaElement.getName();
						System.out.println(elementName);
						
					}
				}
			}
		}
		
		else {// 如果这层不是XmlSchemaSequence则直接获取相应的XmlSchemaAttribute如图7
			XmlSchemaSimpleType xmlSchemaElementType = (XmlSchemaSimpleType) type;
			xmlSchemaElementType.getQName().getLocalPart();
			/*
			 * List<XmlSchemaAttributeOrGroupRef> xmlSchemaObjectCollection = type.get int
			 * count_att = xmlSchemaObjectCollection.size(); for (int j = 0; j < count_att;
			 * j++) { XmlSchemaObject xmlSchemaObject = xmlSchemaObjectCollection.get(j); if
			 * (xmlSchemaObject instanceof XmlSchemaAttribute) { XmlSchemaAttribute
			 * xmlSchemaAttribute = (XmlSchemaAttribute) xmlSchemaObject; String
			 * attributeName = xmlSchemaAttribute.getName(); QName xmlSchemaTypename =
			 * xmlSchemaAttribute.getSchemaTypeName(); String atttype =
			 * xmlSchemaTypename.getLocalPart(); // value.add(attributeName);
			 * System.out.println(attributeName); } }
			 */
		}

	}
}
