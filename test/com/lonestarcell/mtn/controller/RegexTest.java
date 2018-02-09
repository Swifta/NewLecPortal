package com.lonestarcell.mtn.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class RegexTest {
	
	private static Logger log = LogManager.getLogger();
	
	@Test
	public void testAllCharacterMatch(){
		String value = "good       ing";
		value = value.replaceAll( " ", "" );
		log.info( "Cleaned value: "+value );
		boolean status = value.matches( "^[a-zA-Z0-9._-]{3,20}$" );
		log.info( "Regex match on?: "+status );
	}

}
