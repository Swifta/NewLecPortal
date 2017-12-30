package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxnDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnDetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DFxFormUIDesign;
import com.lonestarcell.mtn.model.admin.MFx;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class DFxFormUI extends DFxFormUIDesign implements DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private boolean isTryAgain = false;
	private OutTxnDetails data;
	private MFx mFx;
	private Logger log = LogManager.getLogger( DFxFormUI.class.getName() );

	public DFxFormUI() {
		init();
	}

	
	public void init() {
		this.data = new OutTxnDetails();
		mFx = new MFx( getCurrentUserId(), getCurrentUserSession() );
		attachCommandListeners();
		setContent();
		showPopup();
		setDefaultUIState();

	}

	private void showPopup() {
		processingPopup = new Window("Exchange Rate Details");
		processingPopup.setContent(this);
		processingPopup.center();
		processingPopup.setClosable( true );
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

				String val = tFNewFx.getValue();
				Double d = 0D;

				if (val == null || val.trim().isEmpty()) {
					lbErrorMsg.setValue("LRD value is required.");
					lbErrorMsg.removeStyleName("sn-display-none");
					//lbNormalMsg.addStyleName("sn-display-none");
					return;
				}

				try {

					d = Double.parseDouble(val);
					if (d <= 0 || d > 300000)
						throw new Exception("");

				} catch (Exception e) {

					lbErrorMsg.setValue("Invalid amount.");
					lbErrorMsg.removeStyleName("sn-display-none");
					//lbNormalMsg.addStyleName("sn-display-none");

					return;
				}

				try {

					tFNewFx.setEnabled(false);
					btnSave.setEnabled(false);
					
					
					In in = new In();
					BData<InTxnDetails> inBData = new BData<>();
					InTxnDetails inTxn = new InTxnDetails();
					inBData.setData( inTxn );
					
					in.setData( inBData );

					Out out = mFx.setNewFx(in, data );

					tFNewFx.setEnabled(true);
					btnSave.setEnabled(true);

					if ( out.getStatusCode() == 100) {
						lbErrorMsg.setValue( out.getMsg() );
						lbErrorMsg.removeStyleName("sn-display-none");
						//lbNormalMsg.addStyleName("sn-display-none");
						return;
					}

					lbNormalMsg.setValue( out.getMsg() );
					//lbNormalMsg.removeStyleName("sn-display-none");
					lbErrorMsg.addStyleName("sn-display-none");

					//cNewFxForm.setVisible(false);
					// btnClose.setVisible(true);
					// btnNewFx.setVisible(true);

					return;

				} catch (Exception e) {

					tFNewFx.setEnabled(true);
					btnSave.setEnabled(true);
					lbErrorMsg.setValue("Oops... error occured.");
					lbErrorMsg.removeStyleName("sn-display-none");
					//lbNormalMsg.addStyleName("sn-display-none");
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
		
		if( this.getCurrentUserProfileId() != 1 && this.getCurrentUserProfileId() != 2  ){
			btnNewFx.setVisible( false );
			btnNewFx.setEnabled( false );
		}

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
				lbNormalMsg.setValue("Enter new Rate below [ USD 1 = LRD ? ]");
				tFNewFx.setValue("");

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
		this.setPropertyDataSource();
		try {
			
			In in = new In();
			BData<InTxnDetails> inBData = new BData<>();
			InTxnDetails inTxn = new InTxnDetails();
			
			
			inBData.setData( inTxn );
			in.setData( inBData );
			
			Out out = mFx.setFxDetails(in, data );

			if ( out.getStatusCode() == 100 ) {
				// TODO
				// UI in error state.
				// @Live
				Notification.show( out.getMsg(),
						Notification.Type.ERROR_MESSAGE);
				return;
			}


		} catch (Exception e) {
			e.printStackTrace();
			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
		}
	}
	
	
	private void setPropertyDataSource(){
		
		
		
		Property<String> ds = new ObjectProperty<String>( "-", String.class );
		
		// FX
		
		data.setFxId( ds );
		this.lbRateId.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "0", String.class );
		data.setFxValue( ds );
		this.lbRate.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setFxCreator(ds );
		this.lbSetBy.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setFxTimestamp( ds );
		this.lbTimestamp.setPropertyDataSource( ds );
		
		
		
		// New Fx
		ds = new ObjectProperty<String>( "0", String.class );
		data.setNewFxValue( ds );
		this.tFNewFx.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME).toString(), String.class );
		data.setNewFxCreator(ds );
		
		log.debug( "All ds set successfully." );
		
		
		
		
		
		
		
	}
	
	
	
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}
	
	private int getCurrentUserProfileId(){
		return ( int ) UI.getCurrent().getSession().getAttribute( DLoginUIController.PROFILE_ID );
	}

}
