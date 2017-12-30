package com.lonestarcell.mtn.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InSettings;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutConfig;
import com.lonestarcell.mtn.bean.OutProfile;
import com.lonestarcell.mtn.model.admin.MSettings;
import com.vaadin.data.util.BeanItemContainer;

public class MSettingsTest {

	private static MSettings m;
	private Logger log = LogManager.getLogger( MSettingsTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MSettings( 1L, "qlsp3kc60bdd0jif085s2dilf9" );
	}

	
	@Test
	@Ignore
	public void setProfileTest(){
			
		InSettings inData = new InSettings();
		
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
	
	@Test
	@Ignore
	public void setConfigTest(){
			
		InSettings inData = new InSettings();
		
		inData.setConfig( new OutConfig() );
		
		BData< InSettings > bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.setConfig(in );
		Assert.assertNotNull( out );
		log.debug( "Set config msg: "+out.getMsg() );
		log.debug( "Set config status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	@Test
	// @Ignore
	public void updateConfigTest(){
			
		InSettings inData = new InSettings();
		
		OutConfig data = new OutConfig();
		data.setCoreEPR( "http://127.0.0.1:976667/services/MTNLECPaymentService/" );
		data.setMediatorEPR( "http://127.0.0.1:8280/services/lecproxyx" );
		data.setTimeCorrection( "23:13:59" );
		data.setId( 1 );
		
		inData.setConfig( data );
		
		BData< InSettings > bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.updateConfig(in );
		Assert.assertNotNull( out );
		log.debug( "Set config msg: "+out.getMsg() );
		log.debug( "Set config status: "+out.getStatusCode() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}

}
