package com.elextec.mdm.config;

import javax.servlet.Filter;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.elextec.mdm.filter.LoginFilter;
import com.elextec.mdm.webservice.IMdmService;
import com.elextec.mdm.webservice.impl.MdmService;


/**
 * @author zhangkj
 *
 */
@Configuration
public class WebConfig {
	
	@Bean
	public Filter getLoginFilter() {
		return new LoginFilter();
	}
	
	@Bean
    @Order(Integer.MAX_VALUE)
    public FilterRegistrationBean<Filter> someFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(getLoginFilter());
        registration.addUrlPatterns("/mdm/*");
        //registration.addInitParameter("paramName", "paramValue");
        registration.setName("sessionFilter");
        return registration;
    }
	
	//默认dispatcherServlet
	/*@Bean
	public ServletRegistrationBean<CXFServlet> dispatcherServlet() {
		return new ServletRegistrationBean<CXFServlet>(new CXFServlet(), "/mdm/ws/*");
	}*/
	
	@Bean
	public ServletRegistrationBean<CXFServlet> wsServlet() {
		ServletRegistrationBean<CXFServlet> registration = new ServletRegistrationBean<CXFServlet>();
		registration.setServlet(new CXFServlet());
		registration.addUrlMappings("/mdm/ws/*");
		registration.setName("webService");
		return registration;
	}
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}
	@Bean
	public IMdmService iMdmService() {
		return new MdmService();
	}
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), iMdmService());
		endpoint.publish("/user");
		return endpoint;
	}

	
}
