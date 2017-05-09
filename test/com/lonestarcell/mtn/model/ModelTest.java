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
import com.lonestarcell.mtn.model.admin.Model;
import com.vaadin.data.util.BeanItem;

public class ModelTest {

	private static Model m;
	private Logger log = LogManager.getLogger( ModelTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new Model( 1L, "" );
	}

	@Test
	// @Ignore
	public void setAdminUserIdTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin" );
		inData.setUserSession( "ir6c9d1b4b5gb47u6asfnp244s" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.setAdminUserId( inData.getUsername(), inData.getUserSession() );
		Assert.assertNotNull( out );
		log.debug( "Set admin user id msg: "+out.getMsg() );
		log.debug( "Set admin user id status: "+out.getStatusCode() );
		//log.debug( "Admin user id: "+inData.getRecord().getItemProperty( "userId" ).getValue() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@Test
	@Ignore
	public void setFinanceUserIdTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin3" );
		inData.setUserSession( "v9ql8pjlo6mm2agoqrcl2h6jg2" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.setFinanceUserId( in );
		Assert.assertNotNull( out );
		log.debug( "Set Finance user id msg: "+out.getMsg() );
		log.debug( "Set Finance user id status: "+out.getStatusCode() );
		log.debug( "Finance user id: "+inData.getRecord().getItemProperty( "userId" ).getValue() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	@Test
	@Ignore
	public void setBackofficeUserIdTest(){
			
		InUserDetails inData = new InUserDetails();
		inData.setUsername( "admin2" );
		inData.setUserSession( "v9ql8pjlo6mm2agoqrcl2h6jg2" );
		
		
		inData.setRecord( new BeanItem<>( new OutUserDetails(), OutUserDetails.class ) );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.setBackofficeUserId( in );
		Assert.assertNotNull( out );
		log.debug( "Set backoffice user id msg: "+out.getMsg() );
		log.debug( "Set backoffice user id status: "+out.getStatusCode() );
		log.debug( "Backoffice user id: "+inData.getRecord().getItemProperty( "userId" ).getValue() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}

}
