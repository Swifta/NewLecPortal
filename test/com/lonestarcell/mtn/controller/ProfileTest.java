package com.lonestarcell.mtn.controller;

import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.MUserSelfCare;
import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.user.entity.Profile;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class, DataAccessConfigUser.class, JpaConfig.class } )
public class ProfileTest {
	
	private static Logger log = LogManager.getLogger( ProfileTest.class );
	@Autowired
	private ProfileRepo profileRepo;
	@Autowired
	private ApplicationContext springAppContext;
	
	@Test
	@Transactional
	@Ignore
	public void testFindAll(){
		Assert.assertNotNull( "Profile repo is null.", profileRepo );
		log.debug( "Profile Count "+profileRepo.findAll().size(), profileRepo );
	}
	
	
	@Test
	@Ignore
	@Transactional
	public void testSaveFlush(){
		Assert.assertNotNull( "Profile repo is null.", profileRepo );
		profileRepo.save( new Profile( "Support 2", "Another support 2" ) );
		log.debug( "Profile Count "+profileRepo.findAll().size(), profileRepo );
	}
	
	@Test
	// @Ignore
	public void testSetProfilePermissionSet(){
		
		Assert.assertNotNull( "Apo context is null.", springAppContext );
		MUserSelfCare m = new MUserSelfCare( );
		m.setSpringAppContext( springAppContext );
		Out out = m.setProfilePermissionSet( ( short ) 11, new HashSet< Short >());
		Assert.assertEquals( "Permission added successfully.",1,  out.getStatusCode() );
		
	}
	
	
	
	
	
}
