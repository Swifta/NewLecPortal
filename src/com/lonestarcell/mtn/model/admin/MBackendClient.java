package com.lonestarcell.mtn.model.admin;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;



public class MBackendClient implements MBackendClientInitializable {
	protected static HyperswiftStub stub;
	static {
		setDS();
	}
	protected static void setDS(){
		stub = DSStubInitializer.getDSStub();
	}
	
}
