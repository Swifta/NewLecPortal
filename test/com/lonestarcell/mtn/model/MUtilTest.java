package com.lonestarcell.mtn.model;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.lonestarcell.mtn.model.admin.MUtil;

public class MUtilTest {
	
	private Logger log = LogManager.getLogger( MUtilTest.class.getName() );
	
	
	@Test
	// @Ignore
	public void genNewPassTest(){
		
		String newPass = MUtil.genNewPass();
		Assert.assertNotNull( newPass );
		log.debug( "New gen. pass: "+newPass );
	}
	

}
