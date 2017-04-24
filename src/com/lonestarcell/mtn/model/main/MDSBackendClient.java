package com.lonestarcell.mtn.model.main;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;

public class MDSBackendClient implements MBackendClientInitializable {
	protected static HyperswiftStub stub;
	static {
		setDS();
	}
	private static void setDS(){
		stub = DSStubInitializer.getDSStub();
	}
	
}
