package com.lonestarcell.mtn.controller;

import java.util.Iterator;
import java.util.List;

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
import com.lonestarcell.mtn.spring.config.DataAccessConfig;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.entity.Profile;
import com.lonestarcell.mtn.spring.entity.ProfilePermissionMap;
import com.lonestarcell.mtn.spring.repo.ProfilePermissionMapRepo;
import com.lonestarcell.mtn.spring.repo.ProfileRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class, DataAccessConfig.class, JpaConfig.class } )
public class ProfilePermissionMapTest {
	
	private static Logger log = LogManager.getLogger( ProfilePermissionMapTest.class );
	@Autowired
	private ProfilePermissionMapRepo repo;
	
	@Test
	@Transactional
	@Ignore
	public void testFindAll(){
		Assert.assertNotNull( "Repo is null.", repo );
		
		List< ProfilePermissionMap > lsProfilePermMap = repo.findAll();
		log.debug( "Profile Permission map Count: "+repo.findAll().size(), repo );
		
		Iterator< ProfilePermissionMap > itrProfilePermMap = lsProfilePermMap.iterator();
		while( itrProfilePermMap.hasNext() ){
			ProfilePermissionMap profilePermMap = itrProfilePermMap.next();
			String perm = profilePermMap.getPermission().getName();
			String profile = profilePermMap.getProfile().getProfileName();
			log.debug( profile+" - "+perm, profilePermMap );
			
		}
		
		
	}
	
	
	@Test
	@Transactional
	// @Ignore
	public void testFindByProfileId(){
		Assert.assertNotNull( "Repo is null.", repo );
		
		List< ProfilePermissionMap > lsProfilePermMap = repo.findByProfileProfileId( ( short ) 1 );
		log.debug( "Profile Permission map Count: "+lsProfilePermMap.size(), repo );
		
		Iterator< ProfilePermissionMap > itrProfilePermMap = lsProfilePermMap.iterator();
		while( itrProfilePermMap.hasNext() ){
			ProfilePermissionMap profilePermMap = itrProfilePermMap.next();
			String perm = profilePermMap.getPermission().getName();
			String profile = profilePermMap.getProfile().getProfileName();
			log.debug( profile+" - "+perm, profilePermMap );
			
		}
		
		
	}
	
	
	@Test
	@Transactional
	@Ignore
	public void testSaveFlush(){
		Assert.assertNotNull( "Profile repo is null.", repo );
		//repo.save( new Profile( "Support 2", "Another support 2" ) );
		log.debug( "Profile Count "+repo.findAll().size(), repo );
	}
	
	
	
	
	
}
