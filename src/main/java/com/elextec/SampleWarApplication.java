/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elextec;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Sample WAR application
 */
@RestController
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.elextec.mdm.*")
@ComponentScan({"com.elextec.mdm.*"})
public class SampleWarApplication extends SpringBootServletInitializer {
//public class SampleWarApplication  {

	@RequestMapping("/index")
    public String index() {
		String msg = "Hello Spring Boot,我是MDM";
		return msg;
    }
	
	public static void main(String[] args) throws Exception{
		//System.out.println(SampleWarApplication.class.getResource("/").toString());
		//System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(SampleWarApplication.class, args);
	}

	/*@Bean  
    public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet dispatcherServlet) {  
        ServletRegistrationBean<DispatcherServlet> reg = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet);  
        reg.addUrlMappings("/mdm/*");
        return reg;  
    }*/
	
}
