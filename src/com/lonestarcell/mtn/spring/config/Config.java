package com.lonestarcell.mtn.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.lonestarcell.mtn.controller.main.Person;
import com.lonestarcell.mtn.controller.main.PortalUI;

@Configuration()
//@EnableVaadin
@PropertySource( "classpath:application.properties")
@ComponentScan( basePackages = { "com.lonestarcell.mtn.spring.*" } )
public class Config {
	
	@Bean
	public Person getPerson(){
		return new Person();
	}
	
	@Bean
	public PortalUI.VaadinSpringConfig getVaadinSpringConfig(){
		return new PortalUI.VaadinSpringConfig();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
