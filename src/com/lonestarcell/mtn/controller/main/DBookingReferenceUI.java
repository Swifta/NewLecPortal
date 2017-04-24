package com.lonestarcell.mtn.controller.main;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Validbookingresponse;
import com.lonestarcell.mtn.design.main.DBookingReferenceUIDesign;
import com.lonestarcell.mtn.model.main.MBookingRef;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;

public class DBookingReferenceUI extends DUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bf;

	DBookingReferenceUI(DUI duic, String bf) {
		this.bf = bf;
		init(duic);
	}

	private class DBookingReferenceUIController extends
			DBookingReferenceUIDesign implements DUIControllable {

		DBookingReferenceUIController() {
			init();
		}

		private static final long serialVersionUID = 1L;

		private void validateBookingRef() {

			String bookingRef = fBookingRef.getValue();
			lbNormalMsg.setVisible(true);
			lbErrorMsg.setVisible(false);

			/*
			 * TODO Extra client side validation of the booking reference
			 * required.
			 */

			lbErrorMsg.removeStyleName("sn-invisible");

			if (bookingRef == null) {
				lbErrorMsg.setValue("Enter booking reference and try again.");
				lbNormalMsg.setVisible(false);
				lbErrorMsg.setVisible(true);

				return;
			}

			bookingRef = bookingRef.trim();
			if (bookingRef.isEmpty()) {

				lbErrorMsg.setValue("Enter booking reference and try again.");
				lbNormalMsg.setVisible(false);
				lbErrorMsg.setVisible(true);

				return;
			}

			if (bookingRef.length() > 20 || bookingRef.length() < 10) {
				lbErrorMsg
						.setValue("Invalid input. Please review and try again.");
				lbNormalMsg.setVisible(false);
				lbErrorMsg.setVisible(true);

				return;
			}

			btnNext.setCaption("Proccessing...");
			btnNext.setEnabled(false);

			Validbookingresponse vbr = MBookingRef
					.validateBookingRef(bookingRef);

			btnNext.setCaption("Next");
			btnNext.setEnabled(true);

			if (vbr == null) {
				System.err.println("Booking ref. No data returned.");
				// lbErrorMsg.setValue("Error occured. Please check your connection.");
				lbErrorMsg.setValue("Unauthorized booking reference.");
				// lbNormalMsg.setValue("If problem persists, contact customer support.");
				lbNormalMsg
						.setValue("Session will be closed on multiple invalid attempts.");
				lbNormalMsg.setVisible(false);
				lbErrorMsg.setVisible(true);
				return;
			}

			if (vbr.getResponseCode() == null) {
				System.err.println("No response code returned.");
				lbErrorMsg
						.setValue("Error occured. Please check your connection.");
				lbNormalMsg
						.setValue("If problem persists, contact customer support.");
				lbNormalMsg.setVisible(false);
				lbErrorMsg.setVisible(true);
				return;
			}

			if (!vbr.getResponseCode().equals("01")) {
				System.err.println("Response Code: " + vbr.getResponseCode());
				System.err.println("Response Status: " + vbr.getStatus());
				System.err.println("Booking Ref: " + vbr.getBookingrefid());
				System.err.println("Message: " + vbr.getStatusmessage());

				lbErrorMsg.setValue(vbr.getStatusmessage());

				lbNormalMsg.setVisible(false);
				lbErrorMsg.setVisible(true);
				return;
			}

			if (vbr.getResponseCode().equals("01")
					&& vbr.getStatus().equals("valid")) {
				System.out.println("Valid booking ref.");
				System.out.println("Response Code: " + vbr.getResponseCode());
				System.out.println("Response Status: " + vbr.getStatus());
				System.out.println("Booking Ref: " + vbr.getBookingrefid());

				System.out.println("Ex. Rate: " + vbr.getFxrate());
				System.out.println("Cost in UGX: " + vbr.getConvamount());
				System.out.println("Cost in USD: " + vbr.getRealamount());
				System.out.println("Name: " + vbr.getClientname());

				System.out.println("Response Status: " + vbr.getStatus());
				System.out.println("Booking Ref: " + vbr.getBookingrefid());
				System.out.println("Valid Booking Ref: Moved to DDatailsUI.");

				lbNormalMsg.setValue(vbr.getStatusmessage());

				lbNormalMsg.setVisible(true);
				lbErrorMsg.setVisible(false);

				new DDetailsUI(duic, vbr);

			}

		}

		@Override
		public void attachCommandListeners() {

			attachNext();
			attachCancel();
		}

		@Override
		public void init() {
			setUIState();
			attachCommandListeners();

		}

		private void attachCancel() {
			this.btnCancel.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					UI.getCurrent().getNavigator().navigateTo("");

				}

			});
		}

		private void attachNext() {

			this.btnNext.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					validateBookingRef();

				}

			});
		}

		@Override
		public void setUIState() {

			if (bf != null)
				fBookingRef.setValue(bf);

			this.lbNormalMsg.setVisible(true);
			this.lbErrorMsg.setVisible(false);

			System.out.println("Booking Initial UI state set successully");

		}

	}

	@Override
	public void setContent() {
		swap(duic, new DBookingReferenceUIController());

	}

	@Override
	public void init(DUI duic) {
		this.duic = duic;
		setContent();

	}

}
