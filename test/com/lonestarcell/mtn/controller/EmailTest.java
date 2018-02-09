package com.lonestarcell.mtn.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.email.EmailTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class } )
public class EmailTest {
	
	private static Logger log = LogManager.getLogger( EmailTest.class );
	// @Autowired
	// private EmailServiceImpl emailSender;
	@Autowired
	private ApplicationContext springAppContext;
	
	@Autowired
	private EmailTemplate emailTemplate;
	
	
	@Test
	// @Ignore
	public void sendSimpleMessage(){
		/*
		String content = emailTemplate.build( "acc_creation", templatePart );
		Assert.assertNotNull( "Template is null", content );
		// String content = loader.loadAsString( "acc_creation.html" );
		Assert.assertNotNull( "Template is null", content ); */
		Assert.assertTrue( "Email could not be sent.", emailTemplate.sendCredentials("nambi", "SmU+JKlkk", "pkigozi@swifta.com") );
		log.info( "Email send request issued." );
	}
	
}
