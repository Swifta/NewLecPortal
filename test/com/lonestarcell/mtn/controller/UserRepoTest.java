package com.lonestarcell.mtn.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.Pager;
import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.user.entity.Organization;
import com.lonestarcell.mtn.spring.user.entity.Profile;
import com.lonestarcell.mtn.spring.user.entity.ProfilePermissionMap;
import com.lonestarcell.mtn.spring.user.entity.User;
import com.lonestarcell.mtn.spring.user.repo.ProfilePermissionMapRepo;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.lonestarcell.mtn.spring.user.repo.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class, DataAccessConfigUser.class, JpaConfig.class } )
public class UserRepoTest {
	
	private static Logger log = LogManager.getLogger( UserRepoTest.class );
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ProfileRepo profileRepo;
	

	@Autowired
	private ProfilePermissionMapRepo ppMapRepo;
	
	
	@Test
	@Transactional
	@Ignore
	public void testCount(){
		Assert.assertNotNull( "User repo is null.", userRepo );
		
		log.info( "User count "+userRepo.count(), this );
	}
	
	
	@Test
	@Transactional
	@Ignore
	public void testCountLoggedIn(){
		Assert.assertNotNull( "User repo is null.", userRepo );
		
		log.info( "User count "+userRepo.countActive(), this );
	}
	
	
	@Test
	@Ignore
	public void testFindPageByDateRange() throws ParseException{
			
		
		Page< User > pg = userRepo.findPageByDateRange( new Pager().getPageRequest(1), 1L, DateFormatFac.toDate( "2016-01-01" ), DateFormatFac.toDate( "2018-01-01" ) );

		Assert.assertNotNull( "Page object is null.", pg );
		Assert.assertTrue( "Page count is 0", pg.getSize() > 0 );
		
		Iterator< User > itr = pg.getContent().iterator();
		while( itr.hasNext() ){
			User user = itr.next();
			Organization org = user.getOrganization();
			Profile profile = user.getProfile();
			
			Assert.assertNotNull( "Org object is null.", org );
			Assert.assertNotNull( "Profile object is null.", profile );
			
		}
		log.info( "User data loaded successfully." );
		//Assert.assertNotNull( out.getData() );
		// Assert.assertNotNull( out.getData().getData() );
		
	}
	
	
	@Test
	@Ignore
	public void testFindPageByUsername() throws ParseException{
			
		
		Page< User > pg = userRepo.findPageByUsername( new Pager().getPageRequest(1), 1L, "admin",DateFormatFac.toDate( "2016-01-01" ), DateFormatFac.toDate( "2018-01-01" ) );

		Assert.assertNotNull( "Page object is null.", pg );
		Assert.assertTrue( "Page count is 0", pg.getSize() > 0 );
		
		Iterator< User > itr = pg.getContent().iterator();
		while( itr.hasNext() ){
			User user = itr.next();
			Organization org = user.getOrganization();
			Profile profile = user.getProfile();
			
			Assert.assertNotNull( "Org object is null.", org );
			Assert.assertNotNull( "Profile object is null.", profile );
			
		}
		log.info( "User data loaded successfully." );
		//Assert.assertNotNull( out.getData() );
		// Assert.assertNotNull( out.getData().getData() );
		
	}
	
	
	@Test
	@Ignore
	public void testFindPageByStatus() throws ParseException{
			
		
		Page< User > pg = userRepo.findPageByStatus( new Pager().getPageRequest(1), 1L, ( short ) 1,DateFormatFac.toDate( "2016-01-01" ), DateFormatFac.toDate( "2018-01-01" ) );

		Assert.assertNotNull( "Page object is null.", pg );
		Assert.assertTrue( "Page count is 0", pg.getSize() > 0 );
		
		Iterator< User > itr = pg.getContent().iterator();
		while( itr.hasNext() ){
			User user = itr.next();
			Organization org = user.getOrganization();
			Profile profile = user.getProfile();
			
			Assert.assertNotNull( "Org object is null.", org );
			Assert.assertNotNull( "Profile object is null.", profile );
			
		}
		log.info( "User data loaded successfully." );
		//Assert.assertNotNull( out.getData() );
		// Assert.assertNotNull( out.getData().getData() );
		
	}
	
	
	@Test
	@Ignore
	public void testFindPageByEmail() throws ParseException{
			
		
		Page< User > pg = userRepo.findPageByEmail( new Pager().getPageRequest(1), 1L, "pkigozi",DateFormatFac.toDate( "2016-01-01" ), DateFormatFac.toDate( "2018-01-01" ) );

		Assert.assertNotNull( "Page object is null.", pg );
		Assert.assertTrue( "Page count is 0", pg.getSize() > 0 );
		
		Iterator< User > itr = pg.getContent().iterator();
		while( itr.hasNext() ){
			User user = itr.next();
			Organization org = user.getOrganization();
			Profile profile = user.getProfile();
			
			Assert.assertNotNull( "Org object is null.", org );
			Assert.assertNotNull( "Profile object is null.", profile );
			
		}
		log.info( "User data loaded successfully." );
		//Assert.assertNotNull( out.getData() );
		// Assert.assertNotNull( out.getData().getData() );
		
	}
	
	@Test
	@Ignore
	public void testFindPageByOrg() throws ParseException{
			
		
		Page< User > pg = userRepo.findPageByOrg( new Pager().getPageRequest(1), 1L, "MTN",DateFormatFac.toDate( "2016-01-01" ), DateFormatFac.toDate( "2018-01-01" ) );

		Assert.assertNotNull( "Page object is null.", pg );
		Assert.assertTrue( "Page count is 0", pg.getSize() > 0 );
		
		Iterator< User > itr = pg.getContent().iterator();
		while( itr.hasNext() ){
			User user = itr.next();
			Organization org = user.getOrganization();
			Profile profile = user.getProfile();
			
			Assert.assertNotNull( "Org object is null.", org );
			Assert.assertNotNull( "Profile object is null.", profile );
			
		}
		log.info( "User data loaded successfully." );
		//Assert.assertNotNull( out.getData() );
		// Assert.assertNotNull( out.getData().getData() );
		
	}
	
	
	@Test
	@Ignore
	public void testFindPageByProfile() throws ParseException{
			
		
		Page< User > pg = userRepo.findPageByProfile( new Pager().getPageRequest(1), 1L, "dashboard",DateFormatFac.toDate( "2016-01-01" ), DateFormatFac.toDate( "2018-01-01" ) );

		Assert.assertNotNull( "Page object is null.", pg );
		Assert.assertTrue( "Page count is 0", pg.getSize() > 0 );
		
		Iterator< User > itr = pg.getContent().iterator();
		while( itr.hasNext() ){
			User user = itr.next();
			Organization org = user.getOrganization();
			Profile profile = user.getProfile();
			
			Assert.assertNotNull( "Org object is null.", org );
			Assert.assertNotNull( "Profile object is null.", profile );
			
		}
		log.info( "User data loaded successfully." );
		//Assert.assertNotNull( out.getData() );
		// Assert.assertNotNull( out.getData().getData() );
		
	}
	
	
	@Test
	@Ignore
	public void testFindAByProfileId() throws ParseException{
			
		
		List< User > uList = userRepo.findByProfileProfileId(  ( short ) 1 );

		Assert.assertNotNull( "List object is null.", uList );
		Assert.assertTrue( "List count is 0", uList.size() > 0 );
		
		Iterator< User > itr = uList.iterator();
		while( itr.hasNext() ){
			User user = itr.next();
			Profile profile = user.getProfile();
			Assert.assertNotNull( "Profile object is null.", profile );
			log.info( "Username loaded: "+user.getUsername() );
			
		}
		log.info( "User data loaded successfully." );
		//Assert.assertNotNull( out.getData() );
		// Assert.assertNotNull( out.getData().getData() );
		
	}
	
	
	@Test
	@Ignore
	public void testResetProfileId() throws ParseException{
			
		
		List< User > uList = userRepo.findByProfileProfileId(  ( short ) 1 );

		Assert.assertNotNull( "List object is null.", uList );
		Assert.assertTrue( "List count is 0", uList.size() > 0 );
		
		
		List< User > uListUpdate = new ArrayList<>( uList.size() );
		Iterator< User > itr = uList.iterator();
		while( itr.hasNext() ){
			User user = itr.next();
			Profile profile = new Profile();
			profile.setProfileId( ( short )4 );
			user.setProfile(profile);
			uListUpdate.add( user );
			
			Assert.assertNotNull( "Profile object is null.", profile );
			log.info( "Username loaded: "+user.getUsername() );
		}
		
		userRepo.save( uListUpdate );
		log.info( "User profile updated successfully." );
		//Assert.assertNotNull( out.getData() );
		// Assert.assertNotNull( out.getData().getData() );
		
	}
	
	
	
	@Transactional
	@Test
	// @Ignore
	// @Commit()
	public void testDeleteProfile() throws ParseException{
		
		
		
		
		/*
		Assert.assertNotNull( "Delete target profile is null.", profile );
		
		// Remove mapping
		
		List< ProfilePermissionMap > ppMapList = profile.getProfilePermissionMaps();
		Assert.assertNotNull( "Profile permission map list object is null.", ppMapList );
		Assert.assertTrue( "Profile permission map size", ppMapList.size() > 0 );
		
		List< ProfilePermissionMap > ppMapListRemove = new ArrayList<>( ppMapList.size() );
		Iterator< ProfilePermissionMap > itrMap = ppMapList.iterator();
		
		while( itrMap.hasNext() ){
			ProfilePermissionMap map = itrMap.next();
			
			// Profile mapProfile = map.getProfile();
			// map.setProfile( mapProfile );
			ppMapListRemove.add( map );
			// ppMapRepo.delete( map );
		}
		
		ppMapRepo.delete( ppMapListRemove );
		
		log.info( "Role permission map removed successfully." ); */
			
		
		List< User > uList = userRepo.findByProfileProfileId(  ( short ) 18 );

		Assert.assertNotNull( "List object is null.", uList );
		Assert.assertTrue( "List count is 0", uList.size() > 0 );
		
		
		List< User > uListUpdate = new ArrayList<>( uList.size() );
		Iterator< User > itr = uList.iterator();
		
		Profile defaultProfile = profileRepo.findOne( ( short )4 );
		
		while( itr.hasNext() ){
			User user = itr.next();
			user.setProfile(defaultProfile);
			uListUpdate.add( user );
			
			Assert.assertNotNull( "Profile object is null.", defaultProfile );
			log.info( "Username loaded: "+user.getUsername() );
		}
		
		userRepo.save( uListUpdate );
		log.info( "User profile updated successfully." );

		Profile profile = profileRepo.findOne( ( short ) 18 );
		Assert.assertNotNull( "Delete target profile is null.", profile );
		profileRepo.delete( profile );
		log.info( "Profile deleted successfully." );
		
		
	}
	
	
	
	
	
	
}
