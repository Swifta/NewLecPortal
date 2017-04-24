package com.lonestarcell.mtn.controller.admin;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Fxdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Newfxdetails;
import com.lonestarcell.mtn.design.admin.DFxFormUIDesign;
import com.lonestarcell.mtn.model.admin.MFx;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class DFxFormUI extends DFxFormUIDesign implements DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private boolean isTryAgain = false;

	public DFxFormUI() {
		init(null);
	}

	@Override
	public void init(DManUIController duic) {

		attachCommandListeners();
		setContent();
		showPopup();
		setDefaultUIState();

	}

	private void showPopup() {
		processingPopup = new Window("Exchange Rate Details");
		processingPopup.setContent(this);
		processingPopup.center();
		processingPopup.setClosable(false);
		processingPopup.setEnabled(true);
		processingPopup.setModal(true);
		processingPopup.setDraggable(false);
		processingPopup.setResizable(false);
		processingPopup.setSizeUndefined();
		UI.getCurrent().addWindow(processingPopup);
		new TransactionStatusWorkerThread().start();
	}

	private class TransactionStatusWorkerThread extends Thread {

		volatile int x = 0;

		@Override
		public void run() {
			if (x == 0)
				return;

			while (x < 20) {
				if (x == 0)
					return;

				try {

					sleep(15000);

					System.out.println("x: " + x);
					x = x + 1;

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

	private void setDefaultUIState() {
		this.cNewFxForm.setVisible(false);
		this.lbErrorMsg.addStyleName("sn-display-none");
	}

	@Override
	public void attachCommandListeners() {
		attachBtnClose();
		attachBtnNewFx();
		attachBtnCancel();
		attachBtnSave();

	}

	private void attachBtnSave() {
		this.btnSave.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				String val = tFUGX.getValue();
				Double d = 0D;

				if (val == null || val.trim().isEmpty()) {
					lbErrorMsg.setValue("UGX value is required.");
					lbErrorMsg.removeStyleName("sn-display-none");
					lbNormalMsg.addStyleName("sn-display-none");
					return;
				}

				try {

					d = Double.parseDouble(val);
					if (d <= 0 || d > 300000)
						throw new Exception("");

				} catch (Exception e) {

					lbErrorMsg.setValue("Invalid amount.");
					lbErrorMsg.removeStyleName("sn-display-none");
					lbNormalMsg.addStyleName("sn-display-none");

					return;
				}

				try {

					tFUGX.setEnabled(false);
					btnSave.setEnabled(false);

					Newfxdetails details;

					details = MFx.updateFxDetails(d.toString());

					tFUGX.setEnabled(true);
					btnSave.setEnabled(true);

					if (details == null
							|| details.getResponseCode().equals("100")) {
						lbErrorMsg.setValue(details.getResponseMsg());
						lbErrorMsg.removeStyleName("sn-display-none");
						lbNormalMsg.addStyleName("sn-display-none");
						return;
					}

					lbRate.setValue(details.getValue());
					lbSetBy.setValue(details.getAddedBy());
					lbTimestamp
							.setValue(details.getDate().getTime().toString());

					lbNormalMsg.setValue(details.getResponseMsg());
					lbNormalMsg.removeStyleName("sn-display-none");
					lbErrorMsg.addStyleName("sn-display-none");

					cNewFxForm.setVisible(false);
					btnClose.setVisible(true);
					btnNewFx.setVisible(true);

					return;

				} catch (Exception e) {

					tFUGX.setEnabled(true);
					btnSave.setEnabled(true);
					lbErrorMsg.setValue("Oops... error occured.");
					lbErrorMsg.removeStyleName("sn-display-none");
					lbNormalMsg.addStyleName("sn-display-none");
					e.printStackTrace();
				}

			}

		});
	}

	private void attachBtnCancel() {
		this.btnCancel.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				cNewFxForm.setVisible(false);
				btnNewFx.setVisible(true);
				btnClose.setVisible(true);
				processingPopup.center();

			}

		});

	}

	private void attachBtnNewFx() {

		this.btnNewFx.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				cNewFxForm.setVisible(true);
				processingPopup.center();
				btnClose.setVisible(false);
				btnNewFx.setVisible(false);

				lbErrorMsg.addStyleName("sn-display-none");
				lbNormalMsg.removeStyleName("sn-display-none");
				lbNormalMsg.setValue("Enter new Rate below [ USD 1 = UGX ? ]");
				tFUGX.setValue("");

			}

		});

	}

	private void attachBtnClose() {
		this.btnClose.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				/*
				 * TODO
				 * 
				 * @Live Check for form field data before closing.
				 */
				processingPopup.close();

			}

		});
	}

	private void setContent() {
		try {
			Fxdetails details = MFx.getFxDetails();

			if (details == null || details.getResponseCode().equals("100")) {
				// TODO
				// UI in error state.
				// @Live
				processingPopup.close();
				Notification.show("Error occured! Please contact support team",
						Notification.Type.ERROR_MESSAGE);
				return;
			}

			this.lbRate.setValue(details.getValue());
			this.lbSetBy.setValue(details.getAddedBy());
			this.lbTimestamp.setValue(details.getDate().getTime().toString());

		} catch (Exception e) {
			e.printStackTrace();
			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
		}
	}

}
