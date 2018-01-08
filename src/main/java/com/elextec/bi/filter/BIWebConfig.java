package com.elextec.bi.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

/**
 * @author zhangkj
 *
 */
//@Configuration
public class BiWebConfig {

	@Bean
	public Filter getBiLoginFilter() {
		return new BiLoginFilter();
	}

	

	@Bean
//    @Order(Integer.MAX_VALUE)
	public FilterRegistrationBean<Filter> biFilterRegistration() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
		registration.setFilter(getBiLoginFilter());
		registration.addUrlPatterns("/bi/*");
		//registration.addInitParameter("paramName", "paramValue");
		registration.setName("bi_sessionFilter");
		return registration;
	}
	
}
