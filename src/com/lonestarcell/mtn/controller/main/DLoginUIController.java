package com.lonestarcell.mtn.controller.main;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InLogin;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutLogin;
import com.lonestarcell.mtn.bean.OutUserDetails;
import com.lonestarcell.mtn.controller.util.PasswordTFValidator;
import com.lonestarcell.mtn.controller.util.RequiredTFValidator;
import com.lonestarcell.mtn.design.main.DLoginUIDesign;
import com.lonestarcell.mtn.model.admin.MUserSelfCare;
import com.lonestarcell.mtn.model.main.MLogin;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class DLoginUIController extends DLoginUIDesign implements View,
		DUIControllable, DUIInitializable {

	private static final long serialVersionUID = 1L;

	public static final String SESSION_VAR = "sessionVar";
	public static final String USERNAME = "username";
	public static final String USER_ID = "userId";
	public static final String PROFILE_ID = "profileId";
	public static final String TIME_CORRECTION = "timeCorrection";
	
	
	private Logger log = LogManager.getLogger( DLoginUIController.class.getClass().getName() );

	private Thread t;
	private Item record;

	public DLoginUIController() {
		init();
	}
	


	@Override
	public void attachCommandListeners() {
		System.out.println("Controls attached-login.");
		attachBtnLogin();
		attachBtnReset();
		//attachBtnSave();

	}
	
	

	public Item getRecord() {
		return record;
	}



	public void setRecord(Item record) {
		if( record == null ) {
			log.debug( "Record is null" );
		}else {
			log.debug( "Record is NOT null" );
			record.getItemProperty( "username" );
		}
		this.record = record;
	}


	
	private void login() throws Exception {
		
		
		MLogin mLogin = new MLogin();
		
		Out out = null;
		In in = new In();
		
		InLogin inData = new InLogin();
		log.debug( "Username f value: "+fUsername.getValue() );
		log.debug( "Username f ds: "+fUsername.getPropertyDataSource().getValue() );
		log.debug( "Username record ds: "+record.getItemProperty( "username" ) );
		inData.setUsername( ( String ) record.getItemProperty( "username" ).getValue() );
		inData.setPassword( ( String ) record.getItemProperty( "password" ).getValue()  );
		
		BData<InLogin> bData = new BData<>();
		bData.setData( inData );
		
		in.setData( bData );
		
		out = mLogin.login( in );
		
		log.debug( out.getStatusCode() );
		
		if( out.getStatusCode() == 100 ){
			
			notifError( "Could not complete operation. Please try again / Contact support." );
			clearFields();
			return;
			
			
		}

		if ( out.getStatusCode() == 403 ) {
			
			notifError( "Invalid login details. Please verify and try again" );
			clearFields();
			
			return;
		}
		
		

		BData<?> bdata = out.getData();
		OutLogin outData = (OutLogin) bdata.getData();
		
		log.debug( out.getStatusCode() );
		
		
		String userStatus = outData.getStatus();
		if ( userStatus.equals( "2" ) ) {
			
			notifError( "Account is blocked. Please contact admin for assistence" );
			clearFields();
			
			return;
			
		}
		
		
		if ( userStatus.equals( "0" ) ) {
			
			notifError( "Account is inactive. Please contact admin for assistence" );
			clearFields();
			
			return;
			
		}
		
		
		String changePassStatus = outData.getChangePassword();
		if ( changePassStatus.equals( "1" ) ) {
			
			preResetPasswordHandler( outData );
			return;
			
			
		}
		
		
		if( !userStatus.equals( "1" ) && !changePassStatus.equals( "0" ) ) {
			throw new Exception( "Unknown login error." );
			
		} 


		lbFormState.setValue("Success!");
		lbFormStateDesc.setValue( out.getMsg() );
		lbFormState.removeStyleName("sn-error");
		lbFormStateDesc.removeStyleName("sn-error");

		UI.getCurrent().getSession()
				.setAttribute(DLoginUIController.USERNAME, inData.getUsername() );
		UI.getCurrent()
				.getSession()
				.setAttribute(DLoginUIController.SESSION_VAR,
					 outData.getSessionVar() );
		UI.getCurrent()
				.getSession()
				.setAttribute(DLoginUIController.PROFILE_ID,
						outData.getProfileId() );
		
		UI.getCurrent()
		.getSession()
		.setAttribute(DLoginUIController.USER_ID,
				outData.getUserId() );
		
		UI.getCurrent()
		.getSession()
		.setAttribute(DLoginUIController.TIME_CORRECTION,
				outData.getTimeCorrection() );
		
		
		UI.getCurrent().getNavigator().navigateTo( "management" );
	}
	
	
	@SuppressWarnings("unchecked")
	private void preResetPasswordHandler( OutLogin outData ){
		
		clearResetError();
		
		Calendar cal = Calendar.getInstance();
		Date possibleResetDate = outData.getLastLogin();
		cal.setTime( possibleResetDate );
		cal.add( Calendar.MINUTE, 2 );
		possibleResetDate = cal.getTime();
		
		Date now = new Date();
		log.info( "Password reset time limit:"+possibleResetDate.toString() );
		log.info( "Current timestamp:"+now.toString() );
		
		// Check password reset timeout only if previous session is null 
		// [ This is only on user registration & administrator password reset ]
		if( outData.getResetLoginSession() == null && now.compareTo( possibleResetDate ) > 0 ){
			Notification.show( "Temporary credentials have expired. Please contact support for help", Notification.Type.ERROR_MESSAGE );
			UI.getCurrent().getNavigator().navigateTo( "login" );
			return;
		}
		
		
		
		//outData.getLastLogin().com

		UI.getCurrent().getSession()
				.setAttribute("TMP_PASS", record.getItemProperty( "password" ).getValue() );
		UI.getCurrent().getSession()
				.setAttribute("TMP_UN", record.getItemProperty( "username" ).getValue()  );
		UI.getCurrent()
				.getSession()
				.setAttribute( "TMP_SESSION", outData.getSessionVar()  );
		UI.getCurrent()
				.getSession()
				.setAttribute("TMP_PROFILE_ID",
						outData.getProfileId() );

		clearResetFields();
		fUsername.setEnabled(false);
		fUsername.setReadOnly(false);
		fUsername.setVisible(false);

		

		btnLogin.setVisible(false);
		btnReset.setVisible(true);

		fConfirmPassword.setVisible(true);
		fPassword.setCaption("New Password");
		
		fConfirmPassword.setPropertyDataSource( record.getItemProperty( "newPassword" ) );
		
		PasswordTFValidator passValidator = new PasswordTFValidator( "Field required in valid format." );
		passValidator.init( this.lbNormalMsg, this.lbErrorMsg, record.getItemProperty( "password" ) );

		t = new Thread(new PassResetTimeoutThread());
		t.start();

		
	}
	
	
	private void clearResetFields(){
		fPassword.clear();
		fConfirmPassword.clear();
	}
	
	

	
	@SuppressWarnings("unchecked")
	private void clearFields(){
		
		record.getItemProperty( "username" ).setValue( "" );
		record.getItemProperty( "password" ).setValue( "" );
	}
	
	
	
	@SuppressWarnings("unchecked")
	private Out resetUserCreds() throws Exception{
		
		OutUserDetails user = new OutUserDetails();
		
		
		Item record = new BeanItem<>( user );
		
		
		// New username should be set to current username if username checkbox is not on
		
		record.getItemProperty( "newUsername" ).setValue( getCurrentTempUsername() );
		record.getItemProperty( "username" ).setValue( getCurrentTempUsername() );	
		record.getItemProperty( "password" ).setValue( getCurrentTempPass() );	
		record.getItemProperty( "newPassword" ).setValue( fConfirmPassword.getValue() );
		record.getItemProperty( "profileId" ).setValue( getCurrentTempProfileId() );

		
		MUserSelfCare mUserDetails = new MUserSelfCare();
		InUserDetails inData = new InUserDetails();
		
		inData.setUsername( this.getCurrentTempUsername() );
		inData.setUserSession( this.getCurrentTempSession() );
		
		log.debug( "Temp session: "+inData.getUserSession() );
		
		
		inData.setRecord( record );
	
	
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
			
			Out out = mUserDetails.resetUserCreds( in );
			

			if ( out.getStatusCode() != 1 ) {
				
				fConfirmPassword.clear();
				fPassword.clear();
				
				return out;
			}

			login();
			
			
			fConfirmPassword.clear();
			fPassword.clear();
			fUsername.clear();
			
			return out;


	}
	
	
	
	private boolean isFormValid(){
		
		if( this.fUsername.isVisible() )
		if( !this.isRequiredTFValid( this.fUsername ) )
			return false;

		if( !this.isRequiredTFValid( this.fPassword ) )
			return false;
		
		if( this.fConfirmPassword.isVisible() )
		if( !this.isPasswordTFValid( this.fConfirmPassword ) )
			return false;
		
		return true;
	}
	
	
	private boolean isRequiredTFValid (Field<?> tF){
		
		if( !tF.isValid() ){
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				RequiredTFValidator v = (RequiredTFValidator) itr.next();
				msg += v.getErrorMessage();
			}
			notifError( tF.getCaption()+" Error. "+msg );
			
			return false;
			
		} else {
			
			clearError();
		}
		
		return true;
		
	}
	
	private boolean isPasswordTFValid ( Field<?> tF ){
		
		if( !tF.isValid() ){
			
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				PasswordTFValidator v = (PasswordTFValidator) itr.next();
				msg += v.getErrorMessage();
			}
			
			lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
			
			return false;
			
		} else {
			
			lbNormalMsg.removeStyleName("sn-display-none");
			lbErrorMsg.addStyleName("sn-display-none");
			
			
		}
		
		return true;
		
	}
	
	
	private void attachBtnLogin(){
		
		btnLogin.setClickShortcut(KeyCode.ENTER );
		this.btnLogin.addClickListener( new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				try{
					if( isFormValid() ){
						login();
					}
					
				} catch( Exception e){
					notifError( ".Could not complete operation. Please try again / Contact support" );
					e.printStackTrace();
					clearFields();
				}
				
			}
			
		});
		
	}
	
	
	private void attachBtnReset(){
		btnReset.setClickShortcut(KeyCode.ENTER );
		this.btnReset.addClickListener( new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				try{
					if( isFormValid() ){
						resetUserCreds();
					}
					
				} catch( Exception e){
					notifError( ".Could not complete operation. Please try again / Contact support" );
					e.printStackTrace();
					clearResetFields();
				}
				
			}
			
		});
		
	}
	
	
	private void notifError( String errorMsg ){
		
		/*lbFormState.setValue("Oops...");
		// lbFormStateDesc.setValue("Something is not right. Please check your connection or try again");
		lbFormStateDesc
				.setValue("Invalid login details. Please verify and try again");
		lbFormState.addStyleName("sn-error");
		lbFormStateDesc.addStyleName("sn-error");
		fUsername.setValue("");
		fPassword.setValue(""); */
		
		lbFormState.setValue("Oops...");
		
		lbErrorMsg.setValue( errorMsg  );
		lbErrorMsg.removeStyleName("sn-display-none");
		lbNormalMsg.addStyleName("sn-display-none");
	}
	
	private void clearError( ){
		
		lbFormState.setValue("Welcome!");
		// lbNormalMsg.setValue( "Enter username and password." );
		lbNormalMsg.addStyleName("sn-display-none");
		lbErrorMsg.addStyleName("sn-display-none");
		
	}
	
	private void clearResetError(){
		
		lbFormState.setValue("Password Reset Required!");
		
		lbFormStateDesc
				.setValue("Please enter a new password to proceed. <div><span style=\"font-size:smaller; color:#1E6B15;\">(Password reset window will expire shortly)</span></div>");
		lbFormState.removeStyleName("sn-error");
		lbFormStateDesc.removeStyleName("sn-error");
		
		// lbNormalMsg.setValue( "Password Reset Required!" );
		// lbNormalMsg.removeStyleName("sn-display-none");
		lbErrorMsg.addStyleName("sn-display-none");
		
	}
	


	@Override
	public void init() {
		this.setRecord( new BeanItem<>(new OutLogin() ) );
		setContent();

	}

	@Override
	public void enter(ViewChangeEvent event) {
		Object username = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);
		Object sessionVar = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR);
		Object profileId = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID );
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
						lbTimer.setContentMode( ContentMode.HTML );
					}

				});

				while (x != 0) {

					Thread.sleep(1000);
					x = x - 1;

					UI.getCurrent().access(new Runnable() {

						@Override
						public void run() {

							if( x > 30 ){
							lbTimer.setValue("<span style = \"color:#2bdb08;\">This password reset window will expire in "
									+ x + "(s) </span>");
							} else {
								if( x == 20 ){
									lbTimer.addStyleName( "sn-blink" );
								}
								
								lbTimer.setValue("<span style = \"color:#e7140a; \">This password reset window will expire in "
									+ x + "(s) </span>");
								
							}

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
		clearError();
		attachCommandListeners();
		setPropertyDataSource();
		setFooter();
		
	}
	
	@SuppressWarnings("unchecked")
	private void setPropertyDataSource(){
		
		record.getItemProperty( "username" ).setValue( "" );
		this.fUsername.setPropertyDataSource( record.getItemProperty( "username" ) );
		this.fPassword.setPropertyDataSource( record.getItemProperty( "password" ) );
		
		
		
		this.fUsername.addValidator( new RequiredTFValidator( "Field required in valid format." ) );
		this.fPassword.addValidator( new RequiredTFValidator( "Field required in valid format." ) );
		
		
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
		
		// Cool set as property.
		
		this.lbClient.setValue( "&nbspMTN Benin,&nbsp" );
		this.lbCRYearStart.setValue( startYear );
		this.lbCRYearCurrent.setValue( currentYear );
		
		if( startYear.equals( currentYear )) {
			
			this.lbCRYearCurrent.setVisible(false);
			this.lbYearSeparator.setVisible(false);
			
		}
	}
	
	
	private String getCurrentTempUsername(){
		return UI.getCurrent().getSession().getAttribute( "TMP_UN" ).toString();
	}
	
	private String getCurrentTempPass(){
		return UI.getCurrent().getSession().getAttribute( "TMP_PASS" ).toString();
	}
	
	
	private String getCurrentTempSession(){
		return UI.getCurrent().getSession().getAttribute( "TMP_SESSION" ).toString();
	}
	
	private int getCurrentTempProfileId(){
		return (int) UI.getCurrent().getSession().getAttribute( "TMP_PROFILE_ID" );
	}

	@Override
	public void setUIState() {
		// TODO Auto-generated method stub
		
	}

}
