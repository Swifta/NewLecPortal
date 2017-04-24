package com.lonestarcell.mtn.model.admin;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Dashboarddetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Dashboardresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.DashboardresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getdashboarddetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.vaadin.ui.UI;

public class MDashboard extends MBackendClient {

	public static Dashboarddetails getDashboardDetails()
			throws RemoteException, DataServiceFaultException {

		if (stub == null) {
			System.err.println("DS not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		Getdashboarddetails getdsd = new HyperswiftStub.Getdashboarddetails();

		// getdsd.setUsername("admin");
		// getdsd.setLoginSession("jv7244d9ojbm8dpnigra3bkrrr");

		getdsd.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		getdsd.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		DashboardresponseE responseE = stub.getdashboarddetails(getdsd);

		Dashboardresponse response = responseE.getDashboardresponse();
		Dashboarddetails[] details = response.getDashboarddetails();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Dashboard. Details objects: "
				+ details.length);
		System.out.println(" Dashboard msg: " + details[0].getResponseMsg());

		return details[0];

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		try {
			// MPaymentReport.getPaymentReport();
			MDashboard.getDashboardDetails();

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
