package com.lonestarcell.mtn.controller;

import java.text.ParseException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.lonestarcell.mtn.spring.user.entity.User;
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
	// @Ignore
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
	
	
	
	
	
	
}
