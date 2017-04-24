package com.lonestarcell.mtn.model.main;

import java.rmi.RemoteException;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Persittransactionrecord;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Persittransactionrecordresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Persittransactionrecordresponses;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.PersittransactionrecordresponsesE;

public class MInitiatePayment extends MDSBackendClient {

	public static Persittransactionrecordresponse initiatePayment(
			Persittransactionrecord payload) {
		if (stub == null) {
			System.err.println("DS not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		try {

			PersittransactionrecordresponsesE responsesE = stub
					.persittransactionrecord(payload);
			Persittransactionrecordresponses responses = responsesE
					.getPersittransactionrecordresponses();
			Persittransactionrecordresponse[] responseArr = responses
					.getPersittransactionrecordresponse();
			if (responseArr == null) {
				System.out.println("No server response");
				return null;
			}

			System.out.println(responseArr[0].getStatusmessage());
			System.out.println("Transaction id: " + responseArr[0].getTrxnid());
			return responseArr[0];

		} catch (RemoteException | DataServiceFaultException
				| NullPointerException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return null;

	}

	public static void main(String[] args) {

		Persittransactionrecord transactionPayload = new HyperswiftStub.Persittransactionrecord();
		transactionPayload.setItrxnid("0");
		transactionPayload.setIbookingrefid("BU20160513SAS002");
		transactionPayload.setIpartnerstatusid("0");
		transactionPayload.setIpartnertxnid("-1");
		transactionPayload.setIratetxnid("0");
		transactionPayload.setIamount(398732.00);
		transactionPayload.setImsisdn("0778161154");

		MInitiatePayment.initiatePayment(transactionPayload);
	}
}
