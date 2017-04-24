package com.lonestarcell.mtn.controller.main;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Transactionstatusresponse;
import com.lonestarcell.mtn.design.main.DPaymentStateUIDesign;
import com.lonestarcell.mtn.model.main.MTransactionStatus;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class DPaymentStateUIController extends DPaymentStateUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;

	private String bf = null;
	private String msisdn = null;
	private String amount = null;
	private Window processingPopup;
	private boolean isTryAgain = false;

	public DPaymentStateUIController(String bf, String msisdn, String amount) {

		this.bf = bf;
		this.msisdn = msisdn;
		this.amount = amount;
		init();
	}

	@Override
	public void attachCommandListeners() {

		this.btnClose.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (btnClose.getCaption().equals("Close")) {
					processingPopup.close();
					// UI.getCurrent().getNavigator().navigateTo("");
					UI.getCurrent().getPage().reload();
				} else {
					isTryAgain = true;
					processingPopup.close();

				}

			}

		});
	}

	@Override
	public void init() {
		setUIState();
		attachCommandListeners();
		showPopup();
	}

	private void showPopup() {
		processingPopup = new Window("Processing...");
		processingPopup.setContent(this);
		processingPopup.center();
		processingPopup.setClosable(false);
		processingPopup.setEnabled(true);
		processingPopup.setModal(true);
		processingPopup.setDraggable(false);
		processingPopup.setResizable(false);
		processingPopup.setSizeUndefined();
		UI.getCurrent().addWindow(processingPopup);
		this.lbDescription2.setValue("");
		new TransactionStatusWorkerThread().start();
	}

	private class TransactionStatusWorkerThread extends Thread {

		volatile int x = 0;

		@Override
		public void run() {
			while (x < 20) {
				try {
					sleep(15000);

					if (bf == null) {
						System.out.println("Booking ref: No booking ref set");
						return;
					}

					System.out.println("Booking ref: " + bf);

					System.out.println("x: " + x);
					x = x + 1;

					if (getPaymentStatus(bf, amount)) {
						System.out.println("Payment state: finished");

						UI.getCurrent().access(new Runnable() {

							@Override
							public void run() {

								processingPopup.setCaption("Finished.");
								lbPopupDescription
										.setValue("Process has finished. Check below for status.");

							}

						});

						try {
							sleep(30000);
							UI.getCurrent().access(new Runnable() {

								@Override
								public void run() {
									if (!isTryAgain)
										// UI.getCurrent().getNavigator().navigateTo("");
										UI.getCurrent().getPage().reload();

								}

							});

						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						return;
					} else {
						System.out
								.println("Payment state: Still awaiting completion.");
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
					new TransactionStatusWorkerThread().start();
					System.err.println(this.getClass().toString()
							+ " interrupted. Now Restarted! :-?");

				}
			}

			System.out.println("Finished polling.");
			System.out.println("Final call: " + x);

			try {
				sleep(30000);

				UI.getCurrent().access(new Runnable() {

					@Override
					public void run() {
						if (!isTryAgain)
							// UI.getCurrent().getNavigator().navigateTo("");
							UI.getCurrent().getPage().reload();

					}

				});

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private boolean getPaymentStatus(String bookingRef, final String amount) {

		Transactionstatusresponse response = MTransactionStatus
				.getTransactionStatus(bookingRef);

		if (response == null) {
			System.err.println("Booking ref. No data returned.");
		}

		if (bookingRef == null)
			return false;

		bookingRef = bookingRef.trim();
		if (bookingRef.isEmpty())
			return false;

		if (response == null) {
			System.err.println("Booking ref is null");
			return false;
		}

		if (response.getResponseCode() == null) {
			System.err.println("No response code returned.");
			return false;
		}

		if (response.getResponseCode().equals("01")
				&& response.getPstatuscode().equals("01")) {
			System.out.println("Payment Completed.");
			System.out.println("Response Code: " + response.getResponseCode());
			System.out.println("HStatusCode: " + response.getHstatuscode());
			System.out.println("HStatusDesc: " + response.getHstatusdesc());
			System.out.println("PStatusCode" + response.getPstatuscode());
			System.out.println("PStatusCode " + response.getPstatusdesc());
			System.out.println("Booking Ref: " + response.getObookingrefid());

			UI.getCurrent().access(new Runnable() {

				@Override
				public void run() {

					lbState.setValue("Complete!");
					lbDescription.setValue("Y’ello! Payment of UGX " + amount
							+ " made towards Eagle Air.");
					lbDescription2
							.setValue("Thank you for using MTN Mobile Money!");

					lbSpinner.setVisible(false);
					cApprovalSteps.setVisible(false);
					lbMSISDN.setVisible(false);
					btnPhoneIcon.setVisible(false);
					btnSuccess.setVisible(true);
					btnFailed.setVisible(false);
					btnClose.setVisible(true);
					btnClose.setCaption("Close");

					lbRefreshWarning.setVisible(true);

				}

			});

			return true;

		}

		if (response.getResponseCode().equals("01")
				&& response.getPstatuscode().equals("100")) {
			System.out.println("Payment Completed.");
			System.out.println("Response Code: " + response.getResponseCode());
			System.out.println("HStatusCode: " + response.getHstatuscode());
			System.out.println("HStatusDesc: " + response.getHstatusdesc());
			System.out.println("PStatusCode" + response.getPstatuscode());
			System.out.println("PStatusCode " + response.getPstatusdesc());
			System.out.println("Booking Ref: " + response.getObookingrefid());

			UI.getCurrent().access(new Runnable() {

				@Override
				public void run() {

					lbState.setValue("Oops!");
					lbDescription
							.setValue("Y’ello! This transaction cannot be completed due to a general system failure.");
					lbDescription2
							.setValue("We apologize for the inconvenience. Please try again later.");

					lbSpinner.setVisible(false);
					cApprovalSteps.setVisible(false);
					lbMSISDN.setVisible(false);
					btnPhoneIcon.setVisible(false);
					btnSuccess.setVisible(false);
					btnFailed.setVisible(true);
					// btnFailed.addStyleName("sn-display-block");
					btnClose.setVisible(true);
					btnClose.setCaption("Try again/Close");

					btnClose.setEnabled(true);
					lbRefreshWarning.setVisible(true);

				}

			});

			return true;

		}

		if (response.getResponseCode().equals("01")
				&& response.getPstatuscode().equals("529")) {
			System.out.println("Payment Completed.");
			System.out.println("Response Code: " + response.getResponseCode());
			System.out.println("HStatusCode: " + response.getHstatuscode());
			System.out.println("HStatusDesc: " + response.getHstatusdesc());
			System.out.println("PStatusCode" + response.getPstatuscode());
			System.out.println("PStatusCode " + response.getPstatusdesc());
			System.out.println("Booking Ref: " + response.getObookingrefid());

			UI.getCurrent().access(new Runnable() {

				@Override
				public void run() {

					lbState.setValue("Oops!");
					lbDescription
							.setValue("Y’ello! Please confirm that you have enough funds on: "
									+ msisdn + "  to perform transaction.");
					lbDescription2
							.setValue("In case you do, please try again. Thank you.");
					lbMSISDN.setVisible(true);

					lbSpinner.setVisible(false);
					cApprovalSteps.setVisible(false);
					lbMSISDN.setVisible(false);
					btnPhoneIcon.setVisible(false);
					btnSuccess.setVisible(false);
					btnFailed.setVisible(true);
					// btnFailed.addStyleName("sn-display-block");
					btnClose.setVisible(true);
					btnClose.setCaption("Try again/Close");

					btnClose.setEnabled(true);
					lbRefreshWarning.setVisible(true);

				}

			});

			return true;

		}

		if (response.getResponseCode().equals("01")
				&& response.getPstatuscode().equals("105")) {
			System.out.println("Payment Completed.");
			System.out.println("Response Code: " + response.getResponseCode());
			System.out.println("HStatusCode: " + response.getHstatuscode());
			System.out.println("HStatusDesc: " + response.getHstatusdesc());
			System.out.println("PStatusCode" + response.getPstatuscode());
			System.out.println("PStatusCode " + response.getPstatusdesc());
			System.out.println("Booking Ref: " + response.getObookingrefid());

			UI.getCurrent().access(new Runnable() {

				@Override
				public void run() {

					lbState.setValue("Oops!");
					lbDescription
							.setValue("Y’ello! The number: "
									+ msisdn
									+ "  entered is not registered for MTN Mobile Money.");
					lbDescription2
							.setValue("Please visit nearby MTN outlet to register your number.");
					lbMSISDN.setVisible(true);

					lbSpinner.setVisible(false);
					cApprovalSteps.setVisible(false);
					lbMSISDN.setVisible(false);
					btnPhoneIcon.setVisible(false);
					btnSuccess.setVisible(false);
					btnFailed.setVisible(true);
					// btnFailed.addStyleName("sn-display-block");
					btnClose.setVisible(true);
					btnClose.setCaption("Try again/Close");

					btnClose.setEnabled(true);
					lbRefreshWarning.setVisible(true);

				}

			});

			return true;

		}

		if (response.getResponseCode().equals("01")
				&& response.getPstatuscode().equals("102")) {
			System.out.println("Payment Completed.");
			System.out.println("Response Code: " + response.getResponseCode());
			System.out.println("HStatusCode: " + response.getHstatuscode());
			System.out.println("HStatusDesc: " + response.getHstatusdesc());
			System.out.println("PStatusCode" + response.getPstatuscode());
			System.out.println("PStatusCode " + response.getPstatusdesc());
			System.out.println("Booking Ref: " + response.getObookingrefid());

			UI.getCurrent().access(new Runnable() {

				@Override
				public void run() {

					lbState.setValue("Oops!");
					lbDescription.setValue("Y’ello! The number: " + msisdn
							+ " entered is not a recognised MTN Mobile Money.");
					lbDescription2
							.setValue("Please visit nearby MTN outlet for more details.");
					lbMSISDN.setVisible(true);

					lbSpinner.setVisible(false);
					cApprovalSteps.setVisible(false);
					lbMSISDN.setVisible(false);
					btnPhoneIcon.setVisible(false);
					btnSuccess.setVisible(false);
					btnFailed.setVisible(true);
					// btnFailed.addStyleName("sn-display-block");
					btnClose.setVisible(true);
					btnClose.setCaption("Try again/Close");

					btnClose.setEnabled(true);
					lbRefreshWarning.setVisible(true);

				}

			});

			return true;

		}

		System.out.println("Response Code: " + response.getResponseCode());
		System.out.println("HStatusCode: " + response.getHstatuscode());
		System.out.println("HStatusDesc: " + response.getHstatusdesc());
		System.out.println("PStatusCode" + response.getPstatuscode());
		System.out.println("PStatusCode " + response.getPstatusdesc());
		System.out.println("Booking Ref: " + response.getObookingrefid());

		System.out.println();
		System.out.println();

		if (response.getResponseCode().equals("01")
				&& response.getPstatuscode().equals("1000")) {
			UI.getCurrent().access(new Runnable() {

				@Override
				public void run() {

					lbState.setValue("Pending...");
					lbDescription
							.setValue("Y'ello! Follow steps below to complete your transaction on phone number: ");
					cApprovalSteps.setVisible(true);

				}

			});

		}

		return false;
	}

	public static void format() {
		String msisdn = "256778485869";
		String cCode = msisdn.substring(0, 3);

		String cProviderCode = msisdn.substring(3, 6);

		String no = msisdn.substring(6, msisdn.length());

		String fNo = "+" + cCode + " " + cProviderCode + " " + no;
		System.out.println("Fomarted no: " + fNo);

	}

	public static void main(String[] args) {
		/*
		 * new DPaymentStateUIController("", "").new
		 * TransactionStatusWorkerThread().start();
		 */
		format();

	}

	@Override
	public void setUIState() {
		System.out.println("MSISDN: " + msisdn);

		if (msisdn != null) {

			String cCode = msisdn.substring(0, 3);
			String cProviderCode = msisdn.substring(3, 6);
			String no = msisdn.substring(6, msisdn.length());
			this.lbMSISDN
					.setValue("+" + cCode + " " + cProviderCode + " " + no);

			this.btnFailed.setVisible(false);
			this.btnSuccess.setVisible(false);
			this.btnClose.setVisible(false);
			this.lbRefreshWarning.setVisible(false);
			cApprovalSteps.setVisible(false);
		} else {
			return;
		}

	}

}
