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
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.bean.OutUser;
import com.lonestarcell.mtn.model.admin.MUser;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;

public class MUserTest {

	private static MUser m;
	private Logger log = LogManager.getLogger( MUserTest.class.getName() );
	
	@BeforeClass
	public static void init(){
		m = new MUser( 1L, "" );
		Assert.assertNotNull( "Model is null", m );
	}

	
	
	
	
	@Test
	@Ignore
	public void setTxnUsersTest(){
			
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		inTxn.setPage( 1 );
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -300 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		Out out = m.setUsers(in, new BeanItemContainer<>(OutUser.class) );

		Assert.assertNotNull( out );
		//Assert.assertNotNull( out.getData() );
		//Assert.assertNotNull( out.getData().getData() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@Test
	@Ignore
	public void refreshMultiUserRecordTest(){
			
		List< Item > records = new ArrayList<>( 2 );
		OutUser outUser = new OutUser();
		outUser.setUserId( 1L );
		
		Item record = new BeanItem<OutUser>( outUser );
		records.add( record );
		
		outUser = new OutUser();
		outUser.setUserId( 2L );
		record = new BeanItem<OutUser>( outUser );
		records.add( record );
		
		Out out = m.refreshMultiUserRecord( records );
		Assert.assertNotNull( out );
		log.debug( "Refresh user status: "+out.getStatusCode() );
		log.debug( "Refresh user msg: "+out.getMsg() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@Test
	// @Ignore
	public void blockMultiUserRecordTest(){
			
		OutUser outUser = new OutUser();
		outUser.setUserId( 1L );
		
		Item record = new BeanItem<OutUser>( outUser );
		
		
		List< Item > records = new ArrayList<>( 1 );
		records.add( record );
		
		outUser = new OutUser();
		outUser.setUserId( 2L );
		record = new BeanItem<OutUser>( outUser );
		records.add( record );
		
		Out out = m.blockMultiUserRecord( records );
		Assert.assertNotNull( out );
		log.debug( "Block multi user status: "+out.getStatusCode() );
		log.debug( "Block multi user msg: "+out.getMsg() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@Test
	// @Ignore
	public void activateMultiUserRecordTest(){
			
		OutUser outUser = new OutUser();
		outUser.setUserId( 1L );
		
		Item record = new BeanItem<OutUser>( outUser );
		
		
		List< Item > records = new ArrayList<>( 1 );
		records.add( record );
		
		outUser = new OutUser();
		outUser.setUserId( 2L );
		record = new BeanItem<OutUser>( outUser );
		records.add( record );
		
		Out out = m.activateMultiUserRecord( records );
		Assert.assertNotNull( out );
		log.debug( "Activate multi user status: "+out.getStatusCode() );
		log.debug( "Activate multi user msg: "+out.getMsg() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@Test
	// @Ignore
	public void expireSessionMultiUserRecordTest(){
			
		OutUser outUser = new OutUser();
		outUser.setUserId( 1L );
		
		Item record = new BeanItem<OutUser>( outUser );
		
		
		List< Item > records = new ArrayList<>( 1 );
		records.add( record );
		
		outUser = new OutUser();
		outUser.setUserId( 2L );
		record = new BeanItem<OutUser>( outUser );
		records.add( record );
		
		Out out = m.expireSessionMultiUserRecord( records );
		Assert.assertNotNull( out );
		log.debug( "Expire session multi user status: "+out.getStatusCode() );
		log.debug( "Expire session multi user msg: "+out.getMsg() );
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	
	@Test
	// @Ignore
	public void expirePassMultiUserRecordTest(){
			
		OutUser outUser = new OutUser();
		outUser.setUserId( 1L );
		
		Item record = new BeanItem<OutUser>( outUser );
		
		
		List< Item > records = new ArrayList<>( 1 );
		records.add( record );
		
		outUser = new OutUser();
		outUser.setUserId( 2L );
		record = new BeanItem<OutUser>( outUser );
		records.add( record );
		
		Out out = m.expirePassMultiUserRecord( records );
		Assert.assertNotNull( out );
		log.debug( "Expire pass multi user status: "+out.getStatusCode() );
		log.debug( "Expire pass multi user msg: "+out.getMsg() );
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
		
		
		m.searchUserMeta(in, data);
		
		
		Assert.assertNotNull( data );
		log.debug( "Total Records: "+data.getTotalRecord().getValue() );
		log.debug( "Total Revenue: "+data.getTotalRevenue().getValue() );
		//Assert.assertNotNull( out.getData() );
		//Assert.assertNotNull( out.getData().getData() );
		//Assert.assertTrue( out.getStatusCode() == 1 );
		
		
	}
	
	
	
	

}
