package com.lonestarcell.mtn.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class DSettingsUITest {
	
	Logger log = LogManager.getLogger( DSettingsUITest.class.getName() );
	
	@BeforeClass
	public static void init(){
		
	}

	@Test
    @Ignore
	public void todayDateTest() {
		
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		cal.add(Calendar.DAY_OF_MONTH, -1 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		
	}
	
	@Test
	//@Ignore
	public void timeTest() {
		
		String sTime = "23:13:59";
		
			
			String[] time = sTime.split( ":" );
			String sec = time[ 2 ];
			String min = time[ 1 ];
			String hour = time[ 0 ];
			
			Calendar cal = Calendar.getInstance();
			cal.set( Calendar.SECOND, Integer.valueOf( sec ) );
			cal.set( Calendar.MINUTE, Integer.valueOf( min ) );
			cal.set( Calendar.HOUR, Integer.valueOf( hour ) );
			
			
			
			
			
		
		log.debug( "Hour: "+hour );
		log.debug( "Min: "+min );
		log.debug( "Sec: "+sec );
		
		
	}
	
	@Test
	@Ignore
	public void calculatePagesTest(){
		
		int pages = 0;
		Float pageLength = 5F;
		Long total = 1L;
		
		pages = (int)Math.ceil( total/pageLength );
		
		log.debug( "Pages: "+pages );
		
		
	}

}

