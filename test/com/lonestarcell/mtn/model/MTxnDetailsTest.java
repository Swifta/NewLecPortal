package com.lonestarcell.mtn.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxnDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnDetails;
import com.lonestarcell.mtn.model.admin.MTxnDetails;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;

public class MTxnDetailsTest {

	private static MTxnDetails m;
	private Logger log = LogManager.getLogger( MTxnDetailsTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MTxnDetails( 1L, "" );
	}

	
	@Test
	public void setTxnDetailsTest(){
			
		
		In in = new In();
		BData<InTxnDetails> inBData = new BData<>();
		InTxnDetails inTxn = new InTxnDetails();
		
		
		inTxn.setSwiftaId( "125207" );
		log.debug( "Swifta ID: "+inTxn.getSwiftaId() );
		
		
		
		
		inBData.setData( inTxn );
		in.setData( inBData );
		
		OutTxnDetails data = new OutTxnDetails();
		setPropertyDataSource( data );
		
		Out out = m.setTxnDetails( in, data );
		
		Assert.assertNotNull( out );
		//Assert.assertNotNull( out.getData() );
		//Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
		
		
	}
	
	
	private void setPropertyDataSource( OutTxnDetails data ){
		
		
		
		Property<String> ds = new ObjectProperty<String>( "-", String.class );
		
		// ITRON
		data.setItronAmount( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setMeterNo( ds );
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTokenDateCreate( ds );
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTokenDateEnd( ds );
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTokenId( ds );
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTokenStatus( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setVerifDateCreate( ds );
		
		data.setTxnDateCreate( ds );
		data.setSmsDateCreate( ds );
		ds = new ObjectProperty<String>( "-", String.class );
		data.setVerifDateEnd( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setVerifId( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setVerifStatus( ds );
		
		// Req.
		ds = new ObjectProperty<String>( "-", String.class );
		data.setAmount( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setCurrency( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTxnDateEnd( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setMmoId( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setMsisdn( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setRate( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setRateId( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setSwiftaId( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setPayStatus( ds );
		
		
		
		
		
		
		// SMS
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setSmsDateEnd( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setSmsId( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setSmsStatus( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setToken( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setUnits( ds );
		
		
		
		
		
		
		
	}
	

}
