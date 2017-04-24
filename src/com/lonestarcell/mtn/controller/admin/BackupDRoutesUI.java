package com.lonestarcell.mtn.controller.admin;

import com.lonestarcell.mtn.design.admin.DRoutesUIDesign;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class BackupDRoutesUI extends DRoutesUIDesign implements DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private boolean isTryAgain = false;

	public BackupDRoutesUI() {
		init(null);
	}

	@Override
	public void attachCommandListeners() {

		// attachBtnChangeProfile();
		// attachChkUsername();
		attachBtncancel();
		// attachChkPass();
		// attachBtnAdd();

	}

	/*
	 * private void attachBtnAdd() {
	 * 
	 * this.btnAdd.addClickListener(new ClickListener() {
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override public void buttonClick(ClickEvent event) {
	 * 
	 * tFNewUsername.setComponentError(null);
	 * tFCurrentPass.setComponentError(null); tFNewPass.setComponentError(null);
	 * tFConfirmNewPass.setComponentError(null);
	 * 
	 * String newUsername = null; if (chkUsername.getValue()) { newUsername =
	 * tFNewUsername.getValue(); if (newUsername == null ||
	 * newUsername.trim().isEmpty()) { tFNewUsername .setComponentError(new
	 * UserError( "Username required. Or uncheck username checkbox")); return; }
	 * 
	 * if (newUsername.trim().length() < 4 || newUsername.trim().length() > 15)
	 * { tFNewUsername .setComponentError(new UserError(
	 * "Username should be between 5 and 15 characters.")); return; } }
	 * 
	 * String currentPass = null; String newPass = null; currentPass =
	 * tFCurrentPass.getValue(); if (currentPass == null ||
	 * currentPass.trim().isEmpty()) { tFCurrentPass.setComponentError(new
	 * UserError( "Current password required.")); return; }
	 * 
	 * if (chkPass.getValue()) {
	 * 
	 * newPass = tFNewPass.getValue();
	 * 
	 * if (newPass == null || newPass.trim().isEmpty()) { tFNewPass
	 * .setComponentError(new UserError(
	 * "New password required. Or uncheck password checkbox.")); return; }
	 * 
	 * if (newPass.trim().length() < 4 || newPass.trim().length() > 15) {
	 * tFNewPass .setComponentError(new UserError(
	 * "New password should be between 8 and 15 characters.")); return; }
	 * 
	 * String confirmNewPass = tFConfirmNewPass.getValue();
	 * 
	 * if (confirmNewPass == null || confirmNewPass.trim().isEmpty()) {
	 * tFConfirmNewPass .setComponentError(new UserError(
	 * "Confirm New password field value required. Or uncheck password checkbox."
	 * ));
	 * 
	 * return; }
	 * 
	 * if (!confirmNewPass.trim().equals(newPass.trim())) { tFConfirmNewPass
	 * .setComponentError(new UserError(
	 * "Confirm new password field value not matching new password.")); return;
	 * }
	 * 
	 * }
	 * 
	 * Updateusercreds user = new Updateusercreds(); try {
	 * 
	 * user.setPass(currentPass); user.setNewUsername(newUsername);
	 * user.setNewPass(newPass);
	 * 
	 * Updatedusercreds update = MUserMan .updateUserProfileDetails(user); if
	 * (update == null) { lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none"); lbErrorMsg
	 * .setValue("Oops... something is wrong. Please try again"); return; }
	 * 
	 * if (!update.getResponseCode().equals("01")) {
	 * 
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none");
	 * lbErrorMsg.setValue(update.getResponseMsg()); return; }
	 * 
	 * lbNormalMsg.removeStyleName("sn-display-none");
	 * lbErrorMsg.addStyleName("sn-display-none");
	 * lbNormalMsg.setValue(update.getResponseMsg());
	 * 
	 * if (chkUsername.getValue()) { UI.getCurrent() .getSession()
	 * .setAttribute(DLoginUIController.USERNAME, update.getNewUsername());
	 * lbUN.setValue(update.getNewUsername()); }
	 * 
	 * tFCurrentPass.clear(); tFNewUsername.clear(); tFNewPass.clear();
	 * tFConfirmNewPass.clear();
	 * 
	 * } catch (RemoteException | IllegalAccessException |
	 * DataServiceFaultException e) {
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none"); lbErrorMsg
	 * .setValue("Oops... something is wrong. Please try again");
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * });
	 * 
	 * }
	 */

	private void attachBtncancel() {
		this.btnCancel.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				processingPopup.close();

			}

		});
	}

	@Override
	public void init(DManUIController duic) {

		attachCommandListeners();
		showPopup();
		setDefaultUIState();
		setContent();

	}

	private void showPopup() {
		processingPopup = new Window("Details of Places");
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
		// this.cEditProfileForm.setVisible(false);
		// this.btnChangeProfile.setVisible(false);

		this.lbErrorMsg.addStyleName("sn-display-none");

		// chkUsername.setValue(true);
		// chkPass.setValue(true);

	}

	private void setContent() {

	}

}
