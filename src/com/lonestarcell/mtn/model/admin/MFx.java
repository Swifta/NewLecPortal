package com.lonestarcell.mtn.model.admin;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Fxdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Fxresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.FxresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getfxdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Newfxdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Newfxresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.NewfxresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Setnewfx;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.vaadin.ui.UI;

public class MFx extends MBackendClient {

	public static Fxdetails getFxDetails() throws RemoteException,
			DataServiceFaultException {

		if (stub == null) {
			System.err.println("DS not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		Getfxdetails getfxd = new HyperswiftStub.Getfxdetails();

		// getfxd.setUsername("admin");
		// getfxd.setLoginSession("jv7244d9ojbm8dpnigra3bkrrr");

		getfxd.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		getfxd.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		FxresponseE responseE = stub.getfxdetails(getfxd);

		Fxresponse response = responseE.getFxresponse();
		Fxdetails[] details = response.getFxdetails();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Fx. Details objects: "
				+ details.length);
		System.out.println("Total fetched Fx. Details objects: "
				+ details[0].getResponseMsg());

		System.out
				.println("Date: " + details[0].getDate().getTime().toString());

		return details[0];

	}

	public static Newfxdetails updateFxDetails(String fxvalue)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		if (stub == null) {
			System.err.println("DS not initialized.");
			return null;
		}

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!profile.equals("1"))
			throw new IllegalAccessException("Illigal access error");

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		Setnewfx setNewFx = new HyperswiftStub.Setnewfx();

		// getfxd.setUsername("admin");
		// getfxd.setLoginSession("jv7244d9ojbm8dpnigra3bkrrr");

		setNewFx.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		setNewFx.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));
		setNewFx.setVal(fxvalue);

		NewfxresponseE responseE = stub.setnewfx(setNewFx);
		Newfxresponse response = responseE.getNewfxresponse();
		Newfxdetails[] details = response.getNewfxdetails();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Fx. New Details objects: "
				+ details.length);
		System.out.println("Total fetched Fx. New Details objects: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		try {
			// MPaymentReport.getPaymentReport();
			MFx.getFxDetails();

			/*
			 * MessageDigest digest = MessageDigest.getInstance("SHA-256");
			 * String hash =
			 * DatatypeConverter.printHexBinary(digest.digest("a".getBytes()));
			 * System.out.println("Hash: "+hash);
			 */
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
