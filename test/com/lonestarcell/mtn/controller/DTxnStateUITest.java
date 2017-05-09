package com.lonestarcell.mtn.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class DTxnStateUITest {
	
	Logger log = LogManager.getLogger( DTxnStateUITest.class.getName() );
	
	@BeforeClass
	public static void init(){
		
	}

	@Test
	//@Ignore
	public void testTodayDate() {
		
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		cal.add(Calendar.DAY_OF_MONTH, -1 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		
	}
	
	@Test
	public void calculatePages(){
		
		int pages = 0;
		Float pageLength = 5F;
		Long total = 1L;
		
		pages = (int)Math.ceil( total/pageLength );
		
		log.debug( "Pages: "+pages );
		
		
	}

}

