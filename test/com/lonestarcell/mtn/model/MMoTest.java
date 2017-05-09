package com.lonestarcell.mtn.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InMo;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.MMo;

public class MMoTest {

	private static MMo m;
	private Logger log = LogManager.getLogger( MMoTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MMo( 1L, "" );
	}

	
	@Test
	// @Ignore
	public void tokenRetry(){
			
		In in = new In();
		BData<InMo> inBData = new BData<>();
		InMo inMo = new InMo();
		inMo.setMmoId( "19876379" );
		//inMo.setAcctRef( "90099887766" );
		//inMo.setMsisdn( "231888210000" );
		//inMo.setAmount( ( amount*100 )+"" );
		//inMo.setCurrency( "LRD" );
		
		inBData.setData( inMo );
		in.setData( inBData );
		Out out = m.tokenRetry( in );
		
		Assert.assertNotNull( out );
		log.debug(" Token Status: "+out.getStatusCode() );
		log.debug("Token Msg: "+out.getMsg() );
		Assert.assertNotNull( out.getData() );
		Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
		
		
		
	}
	
	
	@Test
	@Ignore
	public void sendSMS(){
		
		In in = new In();
		BData<InMo> inBData = new BData<>();
		InMo inMo = new InMo();
		inMo.setMmoId( "19876379" );
	
		inBData.setData( inMo );
		in.setData( inBData );
		Out out = m.sendSMS( in );
		
		
		Assert.assertNotNull( out );
		log.debug("SMS Status: "+out.getStatusCode() );
		log.debug("SMS Msg: "+out.getMsg() );
		
		Assert.assertNotNull( out.getData() );
		Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
		
		
		
		
	}
	
	
	

}
