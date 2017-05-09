package com.lonestarcell.mtn.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.lonestarcell.mtn.util.Money;

public class MoneyTest {
	
	private Logger log = LogManager.getLogger( MoneyTest.class.getName() );
	@Test
	public void formatTest(){
		log.debug( "Money Double: "+Money.format( 105.0 ) );
	}
}
