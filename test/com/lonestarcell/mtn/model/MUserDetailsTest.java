package com.lonestarcell.mtn.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutUserDetails;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.vaadin.data.util.BeanItem;

public class MUserDetailsTest {

	private static MUserDetails m;
	private Logger log = LogManager.getLogger( MUserDetailsTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MUserDetails( 1L, "" );
	}

	
	@Test
	@Ignore
	public void setUserDetailsTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "ir6c9d1b4b5gb47u6asfnp244s" );
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.setUserDetails(in );
		Assert.assertNotNull( out );
		log.debug( "Set user details msg: "+out.getMsg() );
		log.debug( "Set user details status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void addNewUserTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "ir6c9d1b4b5gb47u6asfnp244s" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		inData.getRecord().getItemProperty( "orgId" ).setValue( 1 );
		inData.getRecord().getItemProperty( "username" ).setValue( "admin4" );
		inData.getRecord().getItemProperty( "email" ).setValue( "admin4@swifta.com" );
		inData.getRecord().getItemProperty( "password" ).setValue( "admin4" );
		inData.getRecord().getItemProperty( "firstName" ).setValue( "Admin4" );
		inData.getRecord().getItemProperty( "lastName" ).setValue( "admin4" );
		inData.getRecord().getItemProperty( "surname" ).setValue( new String() );
		inData.getRecord().getItemProperty( "profileId" ).setValue( 1 );
		
		// From DB
		// inData.getRecord().getItemProperty( "userId" ).setValue( 1 );
		
		// Defaults
		inData.getRecord().getItemProperty( "lastLogin" ).setValue( new String() );
		inData.getRecord().getItemProperty( "changePass" ).setValue( 0 );
		inData.getRecord().getItemProperty( "userSession" ).setValue( new String() );
		
		// Generated
		inData.getRecord().getItemProperty( "passSalt" ).setValue( "" );
		
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.addNewUser( in );
		Assert.assertNotNull( out );
		log.debug( "Add new user msg: "+out.getMsg() );
		log.debug( "Add new user status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
    @Ignore
	public void checkUsernameUniqueTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "anb4lrubejr1driq01utgq2438" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "username" ).setValue( "admin2" );
		inData.getRecord().getItemProperty( "newUsername" ).setValue( "admin2" );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.checkUsernameUnique( in );
		Assert.assertNotNull( out );
		log.debug( "Check Username Unique msg: "+out.getMsg() );
		log.debug( "Check Username Unique status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void checkEmailUniqueTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "anb4lrubejr1driq01utgq2438" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "email" ).setValue( "admin3@swifta.com" );
		inData.getRecord().getItemProperty( "newEmail" ).setValue( "" );
		
		log.debug( "Email: "+inData.getRecord().getItemProperty( "email" ).getValue() );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.checkEmailUnique( in );
		Assert.assertNotNull( out );
		log.debug( "Check Email Unique msg: "+out.getMsg() );
		log.debug( "Check Email Unique status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	

	
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void expireSessionTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "ir6c9d1b4b5gb47u6asfnp244s" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "username" ).setValue( "admin2" );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.expireSession( in );
		Assert.assertNotNull( out );
		log.debug( "Expire session msg: "+out.getMsg() );
		log.debug( "Expire session status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void expirePassTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
	    inData.setUserSession( "ir6c9d1b4b5gb47u6asfnp244s" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "username" ).setValue( "admin2" );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.expirePass( in );
		Assert.assertNotNull( out );
		log.debug( "Expire pass msg: "+out.getMsg() );
		log.debug( "Expire pass status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void blockUserTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "ir6c9d1b4b5gb47u6asfnp244s" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "username" ).setValue( "admin2" );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.blockUser( in );
		Assert.assertNotNull( out );
		log.debug( "Block user msg: "+out.getMsg() );
		log.debug( "Block user status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void activateUserTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "ir6c9d1b4b5gb47u6asfnp244s" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "username" ).setValue( "admin2" );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.activateUser( in );
		Assert.assertNotNull( out );
		log.debug( "Activate user msg: "+out.getMsg() );
		log.debug( "Activate user status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void resetUserPassAdminTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "v9ql8pjlo6mm2agoqrcl2h6jg2" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "newUsername" ).setValue( "admin" );
		inData.getRecord().getItemProperty( "newPassword" ).setValue( "admin" );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.resetUserPassAdmin( in );
		Assert.assertNotNull( out );
		log.debug( "Reset user pass msg: "+out.getMsg() );
		log.debug( "Reset user pass status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	// @Ignore
	public void resetUserProfileAdminTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "u14sk9bh86gsrmo4s4mu4gv6ja" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "profileId" ).setValue( 1 );
		inData.getRecord().getItemProperty( "username" ).setValue( "admin" );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.resetUserProfileAdmin( in );
		Assert.assertNotNull( out );
		log.debug( "Reset user profile msg: "+out.getMsg() );
		log.debug( "Reset user profile status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void resetUserCredsTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "3pb9ujhaelq9p4mm0jqqq4tflu" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "username" ).setValue( "admin2" );
		inData.getRecord().getItemProperty( "password" ).setValue( "admin2" );
		inData.getRecord().getItemProperty( "newUsername" ).setValue( "admin2" );
		inData.getRecord().getItemProperty( "newPassword" ).setValue( "admin2" );
		inData.getRecord().getItemProperty( "profileId" ).setValue( 1 );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.resetUserCreds( in );
		Assert.assertNotNull( out );
		log.debug( "User creds msg: "+out.getMsg() );
		log.debug( "User creds status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void updatePersonalInfoTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "ir6c9d1b4b5gb47u6asfnp244s" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "username" ).setValue( "admin" );
		
		inData.getRecord().getItemProperty( "newFirstName" ).setValue( "Modupe" );
		inData.getRecord().getItemProperty( "newLastName" ).setValue( "Dupskool" );
		inData.getRecord().getItemProperty( "newSurname" ).setValue( "N/A" );
		inData.getRecord().getItemProperty( "newEmail" ).setValue( "md@swifta.com" );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.updateUserPersonalInfo( in );
		Assert.assertNotNull( out );
		log.debug( "Update personal info msg: "+out.getMsg() );
		log.debug( "Update personal info status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void validateCurrentCredsTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "8p98jndiq1jni7m841dc4hj9rq" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		// Form data
		
		inData.getRecord().getItemProperty( "username" ).setValue( "admin2" );
		inData.getRecord().getItemProperty( "password" ).setValue( "admin2" );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.validUserCurrentCreds( in );
		Assert.assertNotNull( out );
		log.debug( "Validate Current Creds msg: "+out.getMsg() );
		log.debug( "Validate Current Creds status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	

}
