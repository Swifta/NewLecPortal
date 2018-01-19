package com.lonestarcell.mtn.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatFac {

	public static Date toDate( String sDate ) throws ParseException{
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		return df.parse( sDate );
	}
	
	public static String toString( Date date ) throws ParseException{
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return df.format( date );
	}
}
