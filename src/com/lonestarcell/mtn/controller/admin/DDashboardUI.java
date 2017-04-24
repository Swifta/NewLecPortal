package com.lonestarcell.mtn.controller.admin;

import java.rmi.RemoteException;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Dashboarddetails;
import com.lonestarcell.mtn.design.admin.DDashboardUIDesign;
import com.lonestarcell.mtn.model.admin.MDashboard;
import com.vaadin.ui.Notification;

public class DDashboardUI extends DManUI {
	public DDashboardUI(DManUIController duic) {
		if (duic == null)
			System.err.println("DDashboardUI duic is null.");
		else
			System.err.println("DDashboardUI duic is NOT null.");

		init(duic);
	}

	private class DDashboardUIController extends DDashboardUIDesign implements
			DUIControllable {

		private static final long serialVersionUID = 1L;

		DDashboardUIController(DManUIController duic) {
			init(duic);
		}

		@Override
		public void init(DManUIController duic) {
			setDUI(duic);
			attachCommandListeners();
			setContent();
		}

		@Override
		public void attachCommandListeners() {
			// TODO Auto-generated method stub

		}

		private void setContent() {

			try {
				Dashboarddetails details = MDashboard.getDashboardDetails();
				if (details == null || details.getResponseCode().equals("100")) {
					Notification.show(
							"Error occured while loading Dashboard data!",
							Notification.Type.ERROR_MESSAGE);
					return;
				}

				String bookings = details.getTotalLoggedBookings();
				String successPayments = details.getTotalSuccessPayments();
				String failedPayments = details.getTotalFailedPayments();
				String otherStatesPayments = details
						.getTotalOtherStatePayments();

				Long t = Long.parseLong(failedPayments)
						+ Long.parseLong(successPayments);

				this.lbCapturedBookings.setValue(bookings);
				this.lbSuccessTrxns.setValue(successPayments);
				this.lbFailedTrxns.setValue(failedPayments);
				this.lbProcessingTrxns.setValue(otherStatesPayments);
				this.lbTotalTrxns.setValue(String.valueOf(t));

			} catch (RemoteException | DataServiceFaultException e) {
				Notification.show("Error occured while retrieving data. ",
						Notification.Type.ERROR_MESSAGE);
				e.printStackTrace();
			}

		}

	}

	@Override
	public void setContent() {
		swap(duic, new DDashboardUIController(duic));

	}

	@Override
	public void init(DManUIController duic) {

		setDUI(duic);
		setContent();

	}
}
