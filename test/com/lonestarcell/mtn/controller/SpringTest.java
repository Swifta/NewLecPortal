package com.lonestarcell.mtn.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lonestarcell.mtn.controller.main.Person;
import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfig;
import com.lonestarcell.mtn.spring.config.JpaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class, DataAccessConfig.class, JpaConfig.class } )
public class SpringTest {
	
	private static Logger log = LogManager.getLogger( SpringTest.class );
	@Autowired
	private Person person;
	
	@Test
	public void testSayHello(){
		Assert.assertNotNull( "Person is null.", person );
		log.debug( person.greet(), person );
	}
	
	
	
}
