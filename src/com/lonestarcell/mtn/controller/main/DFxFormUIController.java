package com.lonestarcell.mtn.controller.main;

import com.lonestarcell.mtn.design.admin.DFxFormUIDesign;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class DFxFormUIController extends DFxFormUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private boolean isTryAgain = false;

	public DFxFormUIController(String bf, String msisdn, String amount) {
		init();
	}

	@Override
	public void init() {
		setUIState();
		attachCommandListeners();
		showPopup();
	}

	private void showPopup() {
		processingPopup = new Window("Rate Details");
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

	@Override
	public void setUIState() {

		this.cNewFxForm.setVisible(true);
	}

	@Override
	public void attachCommandListeners() {
		attachBtnClose();
		attachBtnNewFx();
		attachBtnCancel();
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

}
