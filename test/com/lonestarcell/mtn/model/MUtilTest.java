package com.lonestarcell.mtn.model;

import java.text.ParseException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.lonestarcell.mtn.model.admin.MUtil;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.NumberFormatFac;

public class MUtilTest {
	
	private Logger log = LogManager.getLogger( MUtilTest.class.getName() );
	
	
	@Test
	@Ignore
	public void genNewPassTest(){
		
		String newPass = MUtil.genNewPass();
		Assert.assertNotNull( newPass );
		log.debug( "New gen. pass: "+newPass );
	}
	
	@Test
	@Ignore
	public void testToDateUpperBound() throws ParseException{
		
		Date date = DateFormatFac.toDateUpperBound( "2011-01-14" );
		Assert.assertNotNull( "Date is null", date );
		
		log.debug( "Date Upper bound: "+date.toString());
	}
	
	@Test
	// @Ignore
	public void testToFormatedMoney() throws ParseException{
		
		String fmoney = NumberFormatFac.toMoney( "64341344600" );
		Assert.assertNotNull( "F-Money is null", fmoney );
		
		log.debug( "Formatted money: "+fmoney );
	}
	

}
