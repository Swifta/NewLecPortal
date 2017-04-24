package com.lonestarcell.mtn.model.admin;

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.xml.bind.DatatypeConverter;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getpaymentreport;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Paymenttransaction;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Results0;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Results1;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.UI;

public class MPaymentReport extends MBackendClient {

	public static BeanItemContainer<Paymenttransaction> getPaymentReport()
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

		Getpaymentreport getpr = new HyperswiftStub.Getpaymentreport();
		// getpr.setLoginSession((String)
		// UI.getCurrent().getSession().getAttribute(DLoginUIController.SESSION_VAR));

		Results1 resultsE = stub.getpaymentreport(getpr);
		Results0 results = resultsE.getResults();

		Paymenttransaction[] trxns = results.getPaymenttransaction();

		if (trxns == null || trxns.length == 0) {

			System.out.println("Total fetched Response: 0");

			Collection<Paymenttransaction> list = Collections.emptyList();
			BeanItemContainer<Paymenttransaction> bookings = new BeanItemContainer<>(
					Paymenttransaction.class);
			bookings.addAll(list);
			return bookings;
		}

		System.out.println("Total fetched trxns: " + trxns.length);

		Collection<Paymenttransaction> listTrans = Arrays.asList(trxns);

		BeanItemContainer<Paymenttransaction> transactions = new BeanItemContainer<Paymenttransaction>(
				Paymenttransaction.class);
		transactions.addAll(listTrans);
		return transactions;

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		try {
			MPaymentReport.getPaymentReport();

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			String hash = DatatypeConverter.printHexBinary(digest.digest("a"
					.getBytes()));
			System.out.println("Hash: " + hash);
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
