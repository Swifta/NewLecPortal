package com.lonestarcell.mtn.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

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
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("iamtestingmtn@gmail.com");
	    mailSender.setPassword("atttwhpbmfizywja");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}

}
