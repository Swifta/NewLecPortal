package com.lonestarcell.mtn.model.util;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatFac {

	public static String toMoney(String sMoney) {

		if (sMoney == null)
			sMoney = "";
		double revenue = Double.valueOf(sMoney);
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.format(revenue).replace("$", "");
		return nf.format(revenue).replace("$", "");
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
