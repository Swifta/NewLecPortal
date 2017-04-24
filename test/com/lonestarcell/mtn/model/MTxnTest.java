package com.lonestarcell.mtn.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.MTxn;

public class MTxnTest {

	private static MTxn m;
	private Logger log = LogManager.getLogger( MTxnTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MTxn();
	}

	@Test
	//@Ignore
	public void testMLogin() {
		
		Assert.assertNotNull( "Model is null", m );

	}
	
	@Test
	public void getTxnPaymentTodayTest(){
			
		
		MTxn mTxn = new MTxn();
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -100 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		Out out = mTxn.getPaymentToday(in);
		
		Assert.assertNotNull( out );
		Assert.assertNotNull( out.getData() );
		Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	
	@Test
	public void getTxnTodayTest(){
			
		
		MTxn mTxn = new MTxn();
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -100 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		Out out = mTxn.getTxnToday(in);
		
		Assert.assertNotNull( out );
		Assert.assertNotNull( out.getData() );
		Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@Test
	public void getTokenTodayTest(){
			
		
		MTxn mTxn = new MTxn();
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -100 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		Out out = mTxn.getTokenToday( in );
		
		Assert.assertNotNull( out );
		Assert.assertNotNull( out.getData() );
		Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	

}
