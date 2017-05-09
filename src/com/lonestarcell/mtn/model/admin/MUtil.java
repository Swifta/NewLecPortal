package com.lonestarcell.mtn.model.admin;

import java.math.BigInteger;
import java.security.SecureRandom;

public class MUtil {
	
	public static String genNewPass() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(50, random).toString(32);
	}
	

}
