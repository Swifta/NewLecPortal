package com.lonestarcell.mtn.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfig;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.entity.Profile;
import com.lonestarcell.mtn.spring.repo.ProfileRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class, DataAccessConfig.class, JpaConfig.class } )
public class ProfileTest {
	
	@Value( "${app.benin.db.username}" )
	private String username;
	
	private static Logger log = LogManager.getLogger( ProfileTest.class );
	@Autowired
	private ProfileRepo profileRepo;
	
	@Test
	@Transactional
	@Ignore
	public void testFindAll(){
		Assert.assertNotNull( "Profile repo is null.", profileRepo );
		log.debug( "Profile Count "+profileRepo.findAll().size(), profileRepo );
	}
	
	
	@Test
	@Transactional
	@Ignore
	public void testSaveFlush(){
		Assert.assertNotNull( "Profile repo is null.", profileRepo );
		profileRepo.save( new Profile( "Support 2", "Another support 2" ) );
		log.debug( "Profile Count "+profileRepo.findAll().size(), profileRepo );
	}
	
	@Test
	@Transactional
	@Ignore
	public void testUpdate(){
		
		Assert.assertNotNull( "Profile repo is null.", profileRepo );
		Profile profile = profileRepo.findOne( ( short )1 );
		Assert.assertNotNull( "No such profile.", profile );
		profile.setProfileStatus( ( short ) 1 );
		
		profileRepo.saveAndFlush( profile );
		log.debug( "Profile updated successfully.", profile );
	}
	
	
	@Test
	@Transactional
	public void testDelete(){
		
		Assert.assertNotNull( "Profile repo is null.", profileRepo );
		Profile profile = profileRepo.findOne( ( short )1 );
		Assert.assertNotNull( "No such profile.", profile );
		profileRepo.delete( profile );
		log.debug( "Profile deleted successfully.", profile );
		log.debug( "app.benin.db.username: "+username, profile );
	}
	
	
	
	
	
}
