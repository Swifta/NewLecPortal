package com.lonestarcell.mtn.controller;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lonestarcell.mtn.controller.main.Person;
import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class, DataAccessConfigUser.class, JpaConfig.class } )
public class SpringTest {
	
	private static Logger log = LogManager.getLogger( SpringTest.class );
	@Autowired
	private Person person;
	
	@Autowired 
	private LocalContainerEntityManagerFactoryBean emfb;
;
	@Test
	public void testSayHello() throws SQLException{
		Assert.assertNotNull( "Person is null.", person );
		Assert.assertNotNull( "emfb is null", emfb );
		log.debug( person.greet(), person );
		
		Connection conn = emfb.getDataSource().getConnection();
		Assert.assertNotNull( "Conn is null", conn );
		Assert.assertTrue( "No open connection.", conn.isClosed() );
		
		
	}
}
