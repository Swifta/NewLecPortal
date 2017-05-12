package com.lonestarcell.mtn.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;

public class MTxnTest {

	private static MTxn m;
	private Logger log = LogManager.getLogger( MTxnTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MTxn( 1L, "9qshoakkbcs9u5ssbnh6uij7c8" );
		Assert.assertNotNull( "Model is null", m );
	}

	
	@Test
	@Ignore
	public void getTxnPaymentTodayTest(){
			

		
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
		Out out = m.getPaymentToday(in);
		
		Assert.assertNotNull( out );
		Assert.assertNotNull( out.getData() );
		Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	
	@Test
	@Ignore
	public void setTxnTodayTest(){
			
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		inTxn.setPage( 3 );
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -300 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		Out out = m.setTxnToday(in, new BeanItemContainer<>(OutTxn.class) );
		
		Assert.assertNotNull( out );
		//Assert.assertNotNull( out.getData() );
		//Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	@Ignore
	public void refreshTxnRecordTest(){
			
		
		Item item = new PropertysetItem();
		
		item.addItemProperty( "swiftaId", new ObjectProperty( "125565", String.class ));
		item.addItemProperty( "date", new ObjectProperty( "", String.class ));
		item.addItemProperty( "statusDesc", new ObjectProperty( "", String.class ));
		Out out = m.refreshTxnRecord( item );
		
		Assert.assertNotNull( out );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	@Ignore
	public void refreshMultiTxnRecordTest(){
			
		List<Item> list = new ArrayList<>();
		
		Item item = new PropertysetItem();
		item.addItemProperty( "swiftaId", new ObjectProperty( "125565", String.class ));
		item.addItemProperty( "date", new ObjectProperty( "", String.class ));
		item.addItemProperty( "statusDesc", new ObjectProperty( "", String.class ));
		
		list.add( item );
		
		item = new PropertysetItem();
		item.addItemProperty( "swiftaId", new ObjectProperty( "125563", String.class ));
		item.addItemProperty( "date", new ObjectProperty( "", String.class ));
		item.addItemProperty( "statusDesc", new ObjectProperty( "", String.class ));
		
		list.add( item );
		
		item = new PropertysetItem();
		item.addItemProperty( "swiftaId", new ObjectProperty( "125342", String.class ));
		item.addItemProperty( "date", new ObjectProperty( "", String.class ));
		item.addItemProperty( "statusDesc", new ObjectProperty( "", String.class ));
		
		list.add( item );
				
		Out out = m.refreshMultiTxnRecord( list );
		
		log.debug( "Txn Refresh msg: "+out.getMsg() );
		
		Assert.assertNotNull( out );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	@Test
	@Ignore
	public void getTokenTodayTest(){
			
		
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -1 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		
		
		Out out = m.getTokenToday( in );
		
		Assert.assertNotNull( out );
		Assert.assertNotNull( out.getData() );
		Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	private void setPropertyDataSource( OutTxnMeta data ){
		
		
		
		Property<String> ds = new ObjectProperty<String>( "-", String.class );
		data.setTotalRecord( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTotalRevenue( ds );
		
		
	}
	
	
	@Test
	@Ignore
	public void setTxnMetaTest(){
		
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
		
		OutTxnMeta data = new OutTxnMeta();
		setPropertyDataSource( data );
		
		
		m.setTxnMeta(in, data);
		
		
		Assert.assertNotNull( data );
		log.debug( "Total Records: "+data.getTotalRecord().getValue() );
		log.debug( "Total Revenue: "+data.getTotalRevenue().getValue() );
	
		
		
	}
	
	
	
	@Test
	// @Ignore
	public void searchTxnMetaTest(){
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		inTxn.setSearchStatusDesc( "FAIL" );
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -100 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		
		OutTxnMeta data = new OutTxnMeta();
		setPropertyDataSource( data );
		
		
		m.searchTxnMeta(in, data);
		
		
		Assert.assertNotNull( data );
		log.debug( "Total Records: "+data.getTotalRecord().getValue() );
		log.debug( "Total Revenue: "+data.getTotalRevenue().getValue() );
	
		
		
	}
	
	
	
	@Test
	@Ignore
	public void setDashMetaTest(){
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -300 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		
		OutTxnMeta data = new OutTxnMeta();
		
		Item record = new BeanItem<>( data );
		
		
		
		Out out = m.setDashMeta(in, record );
		
		Assert.assertNotNull( out );
		
		log.debug( "Dash  Info Failed: "+data.getTotalInfoFail() );
		log.debug( "Dash  Info Success: "+data.getTotalInfoSuccess() );
		
		log.debug( "Dash  SMS Failed: "+data.getTotalSMSFail() );
		log.debug( "Dash  SMS Success: "+data.getTotalSMSSuccess() );
		
		log.debug( "Dash  Token Failed: "+data.getTotalTokenFail() );
		log.debug( "Dash  Token Success: "+data.getTotalTokenSuccess() );
		
		log.debug( "Dash  Txt Failed: "+data.getTotalTxnFail() );
		log.debug( "Dash  Txn Success: "+data.getTotalTxnSuccess() );
		
		
		log.debug( "Dash  Meta msg: "+out.getMsg() );
		log.debug( "Dash Meta status: "+out.getStatusCode() );
	
		
		
	}
	
	@Test
	@Ignore
	public void setTxnTodaySearchTest(){
			
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		inTxn.setPage( 1 );
		inTxn.setSearchMoID( "19528186" );
		inTxn.setSearchSID( "81423" );
		inTxn.setSearchMeterNo( "01451503682" );
		inTxn.setSearchMSISDN( "231888210000" );
		inTxn.setSearchStatusDesc( "FAILED" );
		
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -300 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		Out out = m.searchTxnToday(in, new BeanItemContainer<>(OutTxn.class) );
		
		Assert.assertNotNull( out );
		log.debug( "Txn search status: "+out.getStatusCode() );
		log.debug( "Txn search msg: "+out.getMsg() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	

}
