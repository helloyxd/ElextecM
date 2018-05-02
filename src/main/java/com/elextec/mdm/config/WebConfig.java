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
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
		endpoint.publish("/mdmService");
		return endpoint;
	}
	
	
	private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        return corsConfiguration;
    }
	
	@Bean  
    public FilterRegistrationBean<CorsFilter> corsFilter() {  
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
        CorsConfiguration config = new CorsConfiguration();  
        config.setAllowCredentials(true);  
        config.addAllowedOrigin(CorsConfiguration.ALL);  
        config.addAllowedHeader(CorsConfiguration.ALL);  
        config.addAllowedMethod(CorsConfiguration.ALL);  
        source.registerCorsConfiguration("/**", config);  
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));  
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);  
        return bean;  
    }  

    
	/*@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }*/

	
}
