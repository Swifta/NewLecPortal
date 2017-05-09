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
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;

public class MTxnTest {

	private static MTxn m;
	private Logger log = LogManager.getLogger( MTxnTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MTxn( 1L, "" );
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
	// @Ignore
	public void getTxnTodayTest(){
			
		
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
		//Assert.assertNotNull( out.getData() );
		//Assert.assertNotNull( out.getData().getData() );
		//Assert.assertTrue( out.getStatusCode() == 1 );
		
		
	}
	

}
