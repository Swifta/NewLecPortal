package com.lonestarcell.mtn.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxnDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnDetails;
import com.lonestarcell.mtn.model.admin.MFx;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;

public class MFxTest {

	private static MFx m;
	private Logger log = LogManager.getLogger( MFxTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MFx( 1L, "" );
	}

	
	@Test
	@Ignore
	public void setFxDetailsTest(){
			
		
		In in = new In();
		BData<InTxnDetails> inBData = new BData<>();
		InTxnDetails inTxn = new InTxnDetails();
		
		
		inTxn.setSwiftaId( "125207" );
		log.debug( "Swifta ID: "+inTxn.getSwiftaId() );
		
		
		
		
		inBData.setData( inTxn );
		in.setData( inBData );
		
		OutTxnDetails data = new OutTxnDetails();
		setPropertyDataSource( data );
		
		Out out = m.setFxDetails( in, data );
		
		Assert.assertNotNull( out );
		//Assert.assertNotNull( out.getData() );
		//Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
		
		
	}
	
	@Test
	public void setNewFxDetailsTest(){
			
		
		In in = new In();
		BData<InTxnDetails> inBData = new BData<>();
		InTxnDetails inTxn = new InTxnDetails();
		
		
		inTxn.setSwiftaId( "125207" );
		log.debug( "Swifta ID: "+inTxn.getSwiftaId() );
		
		
		
		
		inBData.setData( inTxn );
		in.setData( inBData );
		
		OutTxnDetails data = new OutTxnDetails();
		setPropertyDataSource( data );
		
		data.getNewFxCreator().setValue( "admin" );
		data.getNewFxValue().setValue( "106" );
		
		Out out = m.setNewFx( in, data );
		
		Assert.assertNotNull( out );
		log.debug( "New Fx. status: "+out.getStatusCode() );
		log.debug( "New Fx. msg: "+out.getMsg() );
		
		//Assert.assertNotNull( out.getData() );
		//Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
		
		
	}
	
	private void setPropertyDataSource( OutTxnDetails data ){
		
		
		
		Property<String> ds = new ObjectProperty<String>( "-", String.class );
		
		// FX
		data.setItronAmount( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setFxId( ds );
		
		ds = new ObjectProperty<String>( "0", String.class );
		data.setFxValue( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setFxCreator(ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setFxTimestamp( ds );
		
		// New Fx
		
		ds = new ObjectProperty<String>( "0", String.class );
		data.setNewFxValue( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setNewFxCreator(ds );
		
		
		
		
		
		
		
		
	}
	

}
