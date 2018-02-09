package com.lonestarcell.mtn.model.util;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatFac {

	// private static Logger log = LogManager.getLogger();
	public static String toMoney(String sMoney) {

		if (sMoney == null)
			sMoney = "0";
		//log.debug( "In money: "+sMoney, NumberFormatFac.class );
		
		double revenue = Double.valueOf(sMoney);
		String sign = "";
		if( revenue < 0 ) {
			revenue *= -1;
			sign = "-";
		}
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String formatedMoney = nf.format(revenue).replace("$", "");
		
		// log.debug( "Out money: "+formatedMoney, NumberFormatFac.class );
		return  sign+formatedMoney;
	}

	public static String toThousands(String sNumber) {

		if (sNumber == null)
			sNumber = "";

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		long records = Long.valueOf(sNumber);
		nf = NumberFormat.getNumberInstance(Locale.US);
		return nf.format(records);

	}
}
