package com.lonestarcell.mtn.esb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lonestarcell.mtn.bean.InMo;
import com.lonestarcell.mtn.service.client.MTNMomoClient;

public class MTNMomoClientTest {
	
	private Logger log = LogManager.getLogger( MTNMomoClientTest.class.getName() );
	private static MTNMomoClient momo;
	
	@BeforeClass
	public static void init(){
		
		momo = new MTNMomoClient();
		Assert.assertNotNull( "Momo object cannot be null",  momo );
		
	}
	@Test
	public void testSetSendAXIOMAxis2Request(){
		
		
		try {
		
			double amount = 1500;
			
			InMo inMo = new InMo();
			inMo.setMmoId( "19876377" );
			inMo.setAcctRef( "90099887766" );
			inMo.setMsisdn( "231888210000" );
			inMo.setAmount( ( amount*100 )+"" );
			inMo.setCurrency( "LRD" );
	
			boolean status = momo.setSendAXIOMAxis2Request( inMo );
			
			Assert.assertTrue( status );
			
			log.debug( "Status: " + status );

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
