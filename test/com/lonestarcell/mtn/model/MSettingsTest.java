package com.lonestarcell.mtn.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InSettings;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutProfile;
import com.lonestarcell.mtn.model.admin.MSettings;
import com.vaadin.data.util.BeanItemContainer;

public class MSettingsTest {

	private static MSettings m;
	private Logger log = LogManager.getLogger( MSettingsTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MSettings( 1L, "" );
	}

	
	@Test
	// @Ignore
	public void setUserDetailsTest(){
			
		InSettings inData = new InSettings();
		inData.setUsername( "admin" );
		inData.setUserSession( "ir6c9d1b4b5gb47u6asfnp244s" );
		
		
		inData.setProfileContainer( new BeanItemContainer<>( OutProfile.class) );
		
		BData< InSettings > bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.setProfiles(in );
		Assert.assertNotNull( out );
		log.debug( "Set profiles msg: "+out.getMsg() );
		log.debug( "Set profiles status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}

}
