package com.lonestarcell.mtn.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateFormatFac {

	public static Date toDate( String sDate ) throws ParseException{
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		return df.parse( sDate );
	}
	
	public static Date toDateUpperBound( String sDate ) throws ParseException{
		
		// TODO Do I need to care about timezone/locale?
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = df.parse( sDate );
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		cal.set( Calendar.HOUR, 23 );
		cal.set( Calendar.MINUTE, 59 );
		cal.set( Calendar.SECOND, 59 );
		return cal.getTime();
	}
	
	public static String toString( Date date ) throws ParseException{
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return df.format( date );
	}
	
	
}
