package com.lonestarcell.mtn.util;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Money {
	
	private static Logger log = LogManager.getLogger( Money.class.getName() );
	
	public static String format( Double a){

		BigDecimal b = BigDecimal.valueOf( a );
		b = b.setScale( 2, BigDecimal.ROUND_DOWN );
		
		log.debug( "Formatted Money: "+b.toString() );
		
		return b.toString();
	}

}
