package com.lonestarcell.mtn.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lonestarcell.mtn.controller.main.Person;
import com.lonestarcell.mtn.controller.main.PortalUI;

@Configuration()
//@EnableVaadin
@PropertySource( "classpath:application.properties")
@ComponentScan( basePackages = { "com.lonestarcell.mtn.spring.repo" } )
@EnableJpaRepositories( basePackages = { "com.lonestarcell.mtn.spring.repo" } )
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
