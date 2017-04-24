package com.lonestarcell.mtn.model.main;

import java.rmi.RemoteException;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Get_transaction_status;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Transaction;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.TransactionE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Transactionstatusresponse;

public class MTransactionStatus extends MDSBackendClient {

	public static Transactionstatusresponse getTransactionStatus(String bf) {
		if (stub == null) {
			System.err.println("DS not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		try {

			Get_transaction_status transactionPayload = new HyperswiftStub.Get_transaction_status();

			transactionPayload.setIbookingrefid(bf);
			transactionPayload.setHstatuscode("0");
			transactionPayload.setHstatusdesc("0");
			transactionPayload.setPstatuscode("0");
			transactionPayload.setPstatusdesc("0");
			transactionPayload.setResponseCode("0");

			TransactionE transactionE = stub
					.get_transaction_status(transactionPayload);
			Transaction transaction = transactionE.getTransaction();
			Transactionstatusresponse[] responseArr = transaction
					.getTransactionstatusresponse();

			if (responseArr == null) {
				System.out.println("No server response");
				return null;
			}

			System.out.println(responseArr[0].getHstatuscode());
			System.out.println("HStatusCode: "
					+ responseArr[0].getHstatuscode());
			System.out.println("HStatusDesc: "
					+ responseArr[0].getHstatusdesc());
			System.out.println("PStatusCode: "
					+ responseArr[0].getPstatuscode());
			System.out.println("PStatusDesc: "
					+ responseArr[0].getPstatusdesc());
			System.out.println("responseCode: "
					+ responseArr[0].getResponseCode());
			return responseArr[0];

		} catch (RemoteException | DataServiceFaultException
				| NullPointerException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return null;

	}

	public static void main(String[] args) {

		MTransactionStatus.getTransactionStatus("BU20160513SAS007");
	}
}
