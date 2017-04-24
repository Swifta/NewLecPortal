package com.lonestarcell.mtn.model.main;

import com.eagleairug.onlinepayment.ws.proxy.EaglePaymentTestStub;

public class MPSBackendClient implements MBackendClientInitializable {
	protected static EaglePaymentTestStub stub;
	static {
		setDS();
	}

	private static void setDS() {
		stub = PSStubInitializer.getDSStub();
	}

}
