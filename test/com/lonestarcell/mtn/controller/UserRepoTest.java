package com.lonestarcell.mtn.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.user.repo.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class, DataAccessConfigUser.class, JpaConfig.class } )
public class UserRepoTest {
	
	private static Logger log = LogManager.getLogger( UserRepoTest.class );
	@Autowired
	private UserRepo userRepo;
	
	@Test
	@Transactional
	@Ignore
	public void testCount(){
		Assert.assertNotNull( "User repo is null.", userRepo );
		
		log.info( "User count "+userRepo.count(), this );
	}
	
	
	@Test
	@Transactional
	// @Ignore
	public void testCountLoggedIn(){
		Assert.assertNotNull( "User repo is null.", userRepo );
		
		log.info( "User count "+userRepo.countActive(), this );
	}
	
	
	
	
	
	
}
