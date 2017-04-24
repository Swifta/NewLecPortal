package com.lonestarcell.mtn.controller.main;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Resetpass;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Resetpassword;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InLogin;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutLogin;
import com.lonestarcell.mtn.design.main.DLoginUIDesign;
import com.lonestarcell.mtn.model.admin.MUserMan;
import com.lonestarcell.mtn.model.main.MLogin;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class DLoginUIController extends DLoginUIDesign implements View,
		DUIControllable, DUIInitializable {

	private static final long serialVersionUID = 1L;

	public static final String SESSION_VAR = "sessionVar";
	public static final String USERNAME = "username";
	public static final String PROFILE_ID = "profileId";
	
	private Logger log = LogManager.getLogger( DLoginUIController.class.getClass().getName() );

	private Thread t;

	public DLoginUIController() {
		init();
	}

	@Override
	public void attachCommandListeners() {
		System.out.println("Controls attached-login.");
		attachLogin();
		attachBtnReset();

	}

	private void attachLogin() {

		this.btnLogin.setClickShortcut(KeyCode.ENTER, null);

		this.btnLogin.addClickListener(new ClickListener() {

			private static final long serialVersionUID = -8066548934073593566L;

			@Override
			public void buttonClick(ClickEvent event) {

					try{
						String sUsername = fUsername.getValue();
						String sPassword = fPassword.getValue();
		
						if (sUsername == null || sUsername.trim().isEmpty()) {
		
							lbFormState.setValue("Empty field!");
							lbFormStateDesc.setValue("Username required to login");
							lbFormState.addStyleName("sn-error");
							lbFormStateDesc.addStyleName("sn-error");
							return;
						}
		
						/*
						 * sUsername = sUsername.trim(); sPassword = sPassword.trim();
						 */
		
						if (sPassword == null || sPassword.trim().isEmpty()) {
		
							lbFormState.setValue("Empty field!");
							lbFormStateDesc.setValue("Password required to login");
							lbFormState.addStyleName("sn-error");
							lbFormStateDesc.addStyleName("sn-error");
							return;
						}
		
						if (sUsername.length() > 15 || sPassword.length() > 64) {
							lbFormState.setValue("Invalid value!");
							lbFormStateDesc
									.setValue("Username or password value is invalid");
							lbFormState.addStyleName("sn-error");
							lbFormStateDesc.addStyleName("sn-error");
							return;
						}
		
						MLogin mLogin = new MLogin();
		
						Out out = null;
						In in = new In();
						
						InLogin inData = new InLogin();
						inData.setUsername( sUsername );
						inData.setPassword( sPassword );
						
						BData<InLogin> bData = new BData<>();
						bData.setData( inData );
						
						in.setData( bData );
						
						out = mLogin.login( in );
						
						BData<?> bdata = out.getData();
						OutLogin outData = (OutLogin) bdata.getData();
						
						log.debug( out.getStatusCode() );
						
		 
						if ( out == null || out.getStatusCode() == 100 ) {
							
							lbFormState.setValue("Oops...");
							// lbFormStateDesc.setValue("Something is not right. Please check your connection or try again");
							lbFormStateDesc
									.setValue("Invalid login details. Please verify and try again");
							lbFormState.addStyleName("sn-error");
							lbFormStateDesc.addStyleName("sn-error");
							fUsername.setValue("");
							fPassword.setValue("");
							return;
						}
		
		
		
						if ( out.getStatusCode() != 1 ) {
		
							lbFormState.setValue("Login error!");
							lbFormStateDesc.setValue( out.getMsg() );
							lbFormState.addStyleName("sn-error");
							lbFormStateDesc.addStyleName("sn-error");
		
							if ( out.getStatusCode() == 2 ) {
		
								lbFormState.setValue("Password Reset Required!");
								lbFormStateDesc
										.setValue("Please enter a new password to proceed. <div><span style=\"font-size:smaller; color:#1E6B15;\">(Password change window will expire shortly)</span></div>");
								lbFormState.removeStyleName("sn-error");
								lbFormStateDesc.removeStyleName("sn-error");
		
								UI.getCurrent().getSession()
										.setAttribute("TMP_PASS", sPassword);
								UI.getCurrent().getSession()
										.setAttribute("TMP_UN", sUsername);
								UI.getCurrent()
										.getSession()
										.setAttribute( "TMP_SESSION", outData.getSessionVar() );
								UI.getCurrent()
										.getSession()
										.setAttribute("TMP_PROFILE_ID",
												outData.getProfileId() );
		
								fPassword.clear();
								fUsername.setEnabled(false);
								fUsername.setReadOnly(false);
								fUsername.setVisible(false);
		
								lbUsername.setVisible(false);
								lbPassword.setVisible(false);
		
								btnLogin.setVisible(false);
								btnReset.setVisible(true);
		
								fConfirmPassword.setVisible(true);
								fPassword.setCaption("New Password");
		
								t = new Thread(new PassResetTimeoutThread());
								t.start();
		
								return;
							}
		
							fUsername.setValue("");
							fPassword.setValue("");
		
							return;
						}
		
						;
						
						lbFormState.setValue("Success!");
						lbFormStateDesc.setValue( out.getMsg() );
						lbFormState.removeStyleName("sn-error");
						lbFormStateDesc.removeStyleName("sn-error");
		
						UI.getCurrent().getSession()
								.setAttribute(DLoginUIController.USERNAME, sUsername);
						UI.getCurrent()
								.getSession()
								.setAttribute(DLoginUIController.SESSION_VAR,
									 outData.getSessionVar() );
						UI.getCurrent()
								.getSession()
								.setAttribute(DLoginUIController.PROFILE_ID,
										outData.getProfileId() );
						UI.getCurrent().getNavigator().navigateTo("management");
					
				} catch ( Exception e ) {
					
					lbFormState.setValue( "Error!" );
					lbFormStateDesc.setValue( "UNKNOWN error occured. Please try again or contact support" );
					lbFormState.addStyleName( "sn-error ");
					lbFormStateDesc.addStyleName( "sn-error" );
					
					log.error( e.getMessage() );
					e.printStackTrace();
					
						
				}

			}

		});
	}

	private void attachBtnReset() {

		this.btnReset.setClickShortcut(KeyCode.ENTER, null);

		this.btnReset.addClickListener(new ClickListener() {

			private static final long serialVersionUID = -8066548934073593566L;

			@Override
			public void buttonClick(ClickEvent event) {
				String sUsername = fUsername.getValue();
				String sPassword = fPassword.getValue();
				String sConfirmPassword = fConfirmPassword.getValue();

				if (sUsername == null || sUsername.trim().isEmpty()) {

					lbFormState.setValue("Empty field!");
					lbFormStateDesc.setValue("Username required to login");
					lbFormState.addStyleName("sn-error");
					lbFormStateDesc.addStyleName("sn-error");
					return;
				}

				/*
				 * sUsername = sUsername.trim(); sPassword = sPassword.trim();
				 */

				if (sPassword == null || sPassword.trim().isEmpty()) {

					lbFormState.setValue("Empty field!");
					lbFormStateDesc.setValue("New password required");
					lbFormState.addStyleName("sn-error");
					lbFormStateDesc.addStyleName("sn-error");
					return;
				}

				if (sConfirmPassword == null
						|| sConfirmPassword.trim().isEmpty()) {

					lbFormState.setValue("Empty field!");
					lbFormStateDesc.setValue("Confirm New password required");
					lbFormState.addStyleName("sn-error");
					lbFormStateDesc.addStyleName("sn-error");
					return;
				}

				if (sUsername.length() > 15 || sPassword.length() > 64) {
					lbFormState.setValue("Invalid value!");
					lbFormStateDesc.setValue("Invalid value entered");
					lbFormState.addStyleName("sn-error");
					lbFormStateDesc.addStyleName("sn-error");
					return;
				}

				if (!sConfirmPassword.equals(sPassword)) {

					lbFormState.setValue("Password mismatch!");
					lbFormStateDesc
							.setValue("New and Confirm Password field values don't match");
					lbFormState.addStyleName("sn-error");
					lbFormStateDesc.addStyleName("sn-error");

					return;
				}

				Resetpassword user = new Resetpassword();
				try {

					user.setPass(UI.getCurrent().getSession()
							.getAttribute("TMP_PASS").toString());
					user.setNewPass(sPassword);
					Resetpass response = MUserMan.resetPassword(user);

					if (response == null) {
						lbFormState.setValue("Oops...");
						lbFormStateDesc
								.setValue("Something is not right. Please check your connection or try again");
						lbFormState.addStyleName("sn-error");
						lbFormStateDesc.addStyleName("sn-error");

						fPassword.clear();
						;
						fConfirmPassword.clear();

						if (t != null)
							t.interrupt();

						return;
					}

					System.out.println("Login Code: "
							+ response.getResponseCode());
					System.out.println("Login MSG: "
							+ response.getResponseMsg());

					if (!response.getResponseCode().equals("01")) {

						lbFormState.setValue("Reset error!");
						lbFormStateDesc.setValue(response.getResponseMsg());
						lbFormState.addStyleName("sn-error");
						lbFormStateDesc.addStyleName("sn-error");

						fPassword.clear();
						fConfirmPassword.clear();

						if (t != null)
							t.interrupt();

						return;
					}

					lbFormState.setValue("Success!");
					lbFormStateDesc.setValue(response.getResponseMsg());
					lbFormState.removeStyleName("sn-error");
					lbFormStateDesc.removeStyleName("sn-error");

					fPassword.clear();
					fConfirmPassword.clear();
					fUsername.clear();

					UI.getCurrent()
							.getSession()
							.setAttribute(
									DLoginUIController.USERNAME,
									UI.getCurrent().getSession()
											.getAttribute("TMP_UN"));
					UI.getCurrent()
							.getSession()
							.setAttribute(DLoginUIController.SESSION_VAR,
									response.getNewLoginSession());
					UI.getCurrent()
							.getSession()
							.setAttribute(
									DLoginUIController.PROFILE_ID,
									UI.getCurrent().getSession()
											.getAttribute("TMP_PROFILE_ID"));
					UI.getCurrent().getNavigator().navigateTo("management");

				} catch (Exception e) {

					lbFormState.setValue("Oops...");
					lbFormStateDesc
							.setValue("Something is not right. Please check your connection or try again");
					lbFormState.addStyleName("sn-error");
					lbFormStateDesc.addStyleName("sn-error");

					fPassword.clear();
					;
					fConfirmPassword.clear();

					if (t != null)
						t.interrupt();

					e.printStackTrace();
				}

			}

		});
	}

	@Override
	public void init() {
		
		setContent();

	}

	@Override
	public void enter(ViewChangeEvent event) {
		Object username = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);
		Object sessionVar = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR);
		Object profileId = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);
		if (username != null && sessionVar != null && profileId != null)
			UI.getCurrent().getNavigator().navigateTo("management");

	}

	
	

	private class PassResetTimeoutThread implements Runnable {

		transient int x = 60;

		@Override
		public void run() {

			try {

				UI.getCurrent().access(new Runnable() {

					@Override
					public void run() {
						lbTimer.setVisible(true);
						lbTimer.setValue("");
					}

				});

				while (x != 0) {

					Thread.sleep(1000);
					x = x - 1;

					UI.getCurrent().access(new Runnable() {

						@Override
						public void run() {

							lbTimer.setValue("This password reset window will expire in "
									+ x + "(s)");
							// System.out.println(x+"(s) remaining before timeout");

						}

					});

				}

				UI.getCurrent().access(new Runnable() {

					@Override
					public void run() {

						UI.getCurrent().getNavigator().navigateTo("login");

					}

				});

			} catch (InterruptedException e) {
				System.out.println("Timeout thread interrupted. Restarted.");
				e.printStackTrace();
				UI.getCurrent().access(new Runnable() {

					@Override
					public void run() {

						t = new Thread(new PassResetTimeoutThread());
						t.start();

					}

				});
			}

		}

	}

	@Override
	public void setHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContent() {
		attachCommandListeners();
		setFooter();
		
	}

	@Override
	public void swap(DUI duic, Component cuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(DUI duic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFooter(){
		
		String startYear = "2017";
		
		Calendar cal = Calendar.getInstance();
		String currentYear = cal.get( Calendar.YEAR )+"";
		
		
		this.lbClient.setValue( "&nbspMTN Liberia,&nbsp" );
		this.lbCRYearStart.setValue( startYear );
		this.lbCRYearCurrent.setValue( currentYear );
		
		if( startYear.equals( currentYear )) {
			
			this.lbCRYearCurrent.setVisible(false);
			this.lbYearSeparator.setVisible(false);
			
		}
	}

	@Override
	public void setUIState() {
		// TODO Auto-generated method stub
		
	}

}
