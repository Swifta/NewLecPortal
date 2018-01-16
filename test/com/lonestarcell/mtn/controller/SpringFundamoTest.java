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
import com.lonestarcell.mtn.spring.config.DataAccessConfigFundamo;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class, DataAccessConfigFundamo.class, JpaConfig.class } )
public class SpringFundamoTest {
	
	private static Logger log = LogManager.getLogger( SpringFundamoTest.class );
	@Autowired
	private Transaction001Repo repo;
	
	@Test
	public void testSayHello(){
		Assert.assertNotNull( "Fundamo repo is null.", repo );
		log.debug( "Total transactions: "+repo.findAll().size(), repo );
	}
	
	
	
}
