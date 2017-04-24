package com.lonestarcell.mtn.model.main;

import org.apache.axis2.AxisFault;

import com.eagleairug.onlinepayment.ws.proxy.EaglePaymentTestStub;

public class PSStubInitializer {
	private static EaglePaymentTestStub stub;

	static {
		setDSStub();
	}

	private static void setDSStub() {
		try {
			// String epr =
			// "http://54.209.44.17:8283/services/t/ug.com/eagleairpayment";
			String epr = "https://portal.mfisa.com/services/t/ug.com/eagleairpayment";
			stub = new EaglePaymentTestStub(epr);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}

	public static EaglePaymentTestStub getDSStub() {
		return stub;
	}

}
