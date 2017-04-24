package com.lonestarcell.mtn.model.main;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Details;

public class MRequestPayment extends MPSBackendClient {
	public static Details requestPayment(Object req) {
		if (stub == null) {
			System.err.println("DS not initialized.");
			return null;
		}

		return null;

	}
}
