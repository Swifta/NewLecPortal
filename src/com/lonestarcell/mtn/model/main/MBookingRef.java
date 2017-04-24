package com.lonestarcell.mtn.model.main;

import java.rmi.RemoteException;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Validbookingid;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Validbookingresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Validbookingresponses;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.ValidbookingresponsesE;

public class MBookingRef extends MDSBackendClient {
	private static Validbookingid vbf;

	public void setBookingRefDetails(Validbookingid vbf) {
		MBookingRef.vbf = vbf;
	}

	public static Validbookingid getBookingRefDetails() {
		return vbf;
	}

	public static Validbookingresponse validateBookingRef(String bf) {
		if (stub == null) {
			System.err.println("DS not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		Validbookingid vbf = new HyperswiftStub.Validbookingid();
		vbf.setIbookingrefid(bf);
		vbf.setImsisdn("0");

		try {
			ValidbookingresponsesE vbfRE = stub.validbookingid(vbf);
			Validbookingresponses vbfR = vbfRE.getValidbookingresponses();
			Validbookingresponse[] vbfRArr = vbfR.getValidbookingresponse();

			if (vbfRArr == null)
				return null;

			return vbfRArr[0];

		} catch (RemoteException | DataServiceFaultException
				| NullPointerException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return null;

	}
}
