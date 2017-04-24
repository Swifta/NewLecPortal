package com.lonestarcell.mtn.model.main;

import org.apache.axis2.AxisFault;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;

public class DSStubInitializer {
	private static HyperswiftStub stub;

	static {
		setDSStub();
	}

	private static void setDSStub() {
		try {
			// String epr = "http://54.152.221.50:9764/services/hyperswift/";
			// String epr =
			// "http://54.209.44.17:9767/services/t/ug.com/hyperswift/";
			String epr = "http://127.0.0.1:9767/services/hyperswift/";
			System.out.println("DSS using epr: " + epr);
			stub = new HyperswiftStub(epr);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}

	public static HyperswiftStub getDSStub() {
		return stub;
	}

}
