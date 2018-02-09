package com.lonestarcell.mtn.controller.admin;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InSettings;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutProfile;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.PasswordTFValidator;
import com.lonestarcell.mtn.controller.util.RequiredTFValidator;
import com.lonestarcell.mtn.controller.util.UsernameTFValidator;
import com.lonestarcell.mtn.design.admin.DEditProfileUIDesign;
import com.lonestarcell.mtn.model.admin.MSettings;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class DUserEditProfileUI extends DEditProfileUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;
	

	private Window processingPopup;
	private Logger log = LogManager.getLogger( DUserEditProfileUI.class.getName() );
	private Item record;
	private ApplicationContext springAppContext;
	

	public DUserEditProfileUI( Item record, ApplicationContext springAppContext ) {
		this.setSpringAppContext(springAppContext);
		this.setRecord( record );
		init();
	}
	
	

	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}



	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}



	public Window getProcessingPopup() {
		return processingPopup;
	}



	public void setProcessingPopup(Window processingPopup) {
		this.processingPopup = processingPopup;
	}



	@Override
	public void attachCommandListeners() {
		this.attachBtnCancel();
		this.attachOnClose();
		this.attachChkUsername();
		this.attachChkPass();
		this.attachChkProfile();
		
		//Fields
		this.attachBtnSave();
	}
	
	
	
	
	private void attachBtnSave() {

		this.btnSave.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {

				try {
					
					if( isFormValid() ){
						
						Out out = resetUserProfile();
						if( out.getStatusCode() == 1 ) {
						
							lbNormalMsg.removeStyleName("sn-display-none");
							lbErrorMsg.addStyleName("sn-display-none");
							Notification.show( out.getMsg(),
									Notification.Type.HUMANIZED_MESSAGE );
							
							if( chkProfile.getValue() ){
								setDefaultProfile( (BeanItemContainer<OutProfile>)comboProfile.getContainerDataSource(), ( int )record.getItemProperty( "profileId" ).getValue() );
							}
							
							processingPopup.close();
							
						} else {
							
							lbErrorMsg.removeStyleName("sn-display-none");
							lbNormalMsg.addStyleName("sn-display-none");
							lbErrorMsg.setValue( out.getMsg() );
						}
				
					}
					
					recenterWindow();
				
				} catch ( Exception e){
					
					e.printStackTrace();
					
					Notification.show( "Error occured. Please try again / Contact support",
							Notification.Type.ERROR_MESSAGE );
					lbErrorMsg.removeStyleName("sn-display-none");
					lbNormalMsg.addStyleName("sn-display-none");
					lbErrorMsg.setValue( "Error occured. Please try again / Contact support"  );
				}
				

			}
			

		});

	}
	
	
	private boolean isFormValid(){
		

		if (chkUsername.getValue())
			if( !this.isUsernameTFValid( this.tFNewUsername ) )
				return false;

		/*if( !this.isRequiredTFValid( this.tFCurrentPass ) )
			return false;
		*/
		
		if (chkPass.getValue()) {
			if( !this.isRequiredTFValid( this.tFNewPass ) )
				return false;
			if( !this.isPasswordTFValid( this.tFConfirmNewPass ) )
				return false;
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
	
	
	private boolean isUsernameTFValid ( Field<?> tF ){
		
		if( !tF.isValid() ){
			
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				UsernameTFValidator v = (UsernameTFValidator) itr.next();
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
	
	
	private boolean isRequiredTFValid (Field<?> tF){
		
		if( !tF.isValid() ){
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				RequiredTFValidator v = (RequiredTFValidator) itr.next();
				msg += v.getErrorMessage();
			}
			
			log.debug( "RT is invalid" );
			
			lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			
			log.debug( "RT error set as: "+msg );
			
			return false;
			
		} else {
			
			lbNormalMsg.removeStyleName("sn-display-none");
			lbErrorMsg.addStyleName("sn-display-none");
			
			log.debug( "RT is valid" );
			
			
		}
		
		return true;
		
	}
	
	private void initChk(){
		
		this.chkUsername.setValue( false );
		this.chkProfile.setValue( true );
		
		/*tFNewPass.getParent().addStyleName("sn-display-none");
		tFConfirmNewPass.getParent().addStyleName("sn-display-none");
		comboProfile.getParent().addStyleName("sn-display-none");
		*/
		
		this.lbErrorMsg.addStyleName("sn-display-none");
		
	}
	
	
	private void initComboProfile(){
		
		BeanItemContainer<OutProfile> profiles = getProfiles();
		comboProfile.setNullSelectionAllowed( false );
		comboProfile.setContainerDataSource( profiles );
		comboProfile.setItemCaptionMode( ItemCaptionMode.PROPERTY );
		comboProfile.setItemCaptionPropertyId( "profileName" );
		setDefaultProfile( profiles, ( int )record.getItemProperty( "profileId" ).getValue() );
		
		
		
	}
	
	
	private void setDefaultProfile( BeanItemContainer<OutProfile> profiles, int profileId ){
		
		Iterator< OutProfile > itr = profiles.getItemIds().iterator();
		while( itr.hasNext() ){
			
			OutProfile profile = itr.next();
			
			if( profile.getProfileId() == profileId ) {
				comboProfile.setValue( profile );
				break;
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private Out resetUserProfile(){
		
		// New username should be set to current username if username checkbox is not on
		if( !chkUsername.getValue() ){
			record.getItemProperty( "newUsername" ).setValue( record.getItemProperty( "username" ).getValue() );		
		} else {
			record.getItemProperty( "username" ).setValue( record.getItemProperty( "newUsername" ).getValue().toString() );
		}
		
		// New password should be set to current password if password checkbox is not on
		if( !chkPass.getValue() )
			record.getItemProperty( "newPassword" ).setValue( record.getItemProperty( "password" ).getValue() );

		
		MUserDetails mUserDetails = new MUserDetails(  getCurrentUserId(), getCurrentUserSession() );
		InUserDetails inData = new InUserDetails();
		setAuth( inData );
		
		
		inData.setRecord( record );
		
		if( chkProfile.getValue() ) {
			OutProfile profile = (OutProfile) comboProfile.getValue();
			record.getItemProperty( "profileId" ).setValue( Integer.valueOf( profile.getProfileId() ) );
		} else {
			throw new IllegalStateException( "No profile selected." );
		}
	
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
			
			Out out = mUserDetails.resetUserProfileAdmin( in );
			tFCurrentPass.clear();
			
			
			
			

			if ( out.getStatusCode() != 1 ) {
				return out;
			}

			// UI.getCurrent().getSession().setAttribute( DLoginUIController.USERNAME, inData.getUsername() );
			// UI.getCurrent().getSession().setAttribute( DLoginUIController.SESSION_VAR,  inData.getUserSession() );
			// UI.getCurrent().getSession().setAttribute( DLoginUIController.PROFILE_ID, record.getItemProperty( "profileId" ).getValue() );
			
			return out;


	}
	
	private BeanItemContainer< OutProfile > getProfiles(){
		

		MSettings mSettings = new MSettings(  getCurrentUserId(), getCurrentUserSession()  );
		InSettings inData = new InSettings();
		setSettingsAuth( inData );
		
		inData.setProfileContainer( new BeanItemContainer<>( OutProfile.class) );
		
		BData< InSettings > bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = mSettings.setProfiles(in );
		
		log.debug( "Set profiles msg: "+out.getMsg() );
		log.debug( "Set profiles status: "+out.getStatusCode() );
		
		return inData.getProfileContainer();
	}
	
	
	private void attachChkUsername() {
		tFNewUsername.clear();
		this.chkUsername.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {

				tFNewUsername.setComponentError(null);
				tFNewUsername.clear();
				
				Boolean state = (Boolean) event.getProperty().getValue();
				if (state) {
					tFNewUsername.getParent().removeStyleName("sn-display-none");
				} else {
					tFNewUsername.getParent().addStyleName("sn-display-none");
				}

				if (!chkUsername.getValue() && !chkPass.getValue() && !chkProfile.getValue()) {
					chkUsername.setValue(true);
				}
				
				recenterWindow();

			}

		});
	}
	
	private void attachChkPass() {
		this.chkPass.addValueChangeListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				
				tFCurrentPass.setComponentError(null);
				tFNewPass.setComponentError(null);
				tFConfirmNewPass.setComponentError(null);
				
				tFCurrentPass.clear();
				tFNewPass.clear();
				tFConfirmNewPass.clear();

				Boolean state = (Boolean) event.getProperty().getValue();
				if (state) {
					tFNewPass.getParent().removeStyleName("sn-display-none");
					tFConfirmNewPass.getParent()
							.removeStyleName("sn-display-none");
				} else {
					tFNewPass.getParent().addStyleName("sn-display-none");
					tFConfirmNewPass.getParent().addStyleName("sn-display-none");
				}


				if (!chkUsername.getValue() && !chkPass.getValue() && !chkProfile.getValue()) {
					chkUsername.setValue(true);
				}
				
				recenterWindow();

			}

		});
	}
	
	private void recenterWindow(){
		processingPopup.center();
	}
	
	private void attachChkProfile() {
		this.chkProfile.addValueChangeListener(new ValueChangeListener() {

			
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {

				Boolean state = (Boolean) event.getProperty().getValue();
				if (state) {
					comboProfile.getParent().removeStyleName("sn-display-none");
					
				} else {
					comboProfile.getParent().addStyleName("sn-display-none");
					
				}


				if (!chkUsername.getValue() && !chkPass.getValue() && !chkProfile.getValue()) {
					chkUsername.setValue(true);
				}
				
				recenterWindow();

			}

		});
	}
	
	private void attachBtnCancel(){
		this.btnCancel.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				processingPopup.close();
				refreshRecord();
				
				// new DUserDetailsUI( record );
				
			}
			
		});
	}
	
	private void attachOnClose(){
		processingPopup.addCloseListener( new CloseListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				processingPopup.close();
				refreshRecord();
				
				// new DUserDetailsUI( record );
				
			}
			
		});
	}
	
	
	private boolean refreshRecord(){
		
		
		
		
		if( record != null ) {
			
			InUserDetails inData = new InUserDetails();
			setAuth( inData );
			inData.setRecord( record );
			
			BData<InUserDetails> bData = new BData<>();
			bData.setData( inData );
			
			In in = new In();
			in.setData( bData );
			
			MUserDetails mUserDetails = new MUserDetails(  getCurrentUserId(), getCurrentUserSession() );
			Out out = mUserDetails.setUserDetails(in );
			return out.getStatusCode() == 1;
		
		} 
		
		return false;
		
		
	}
	
	public Item getRecord() {
		return record;
	}

	public void setRecord(Item record) {
		this.record = record;
	}


	private void init() {
		this.initChk();
		this.initComboProfile();
		this.setProcessingPopup( new Window("User Details") );
		attachCommandListeners();
		setPropertyDataSource();
		setContent();
		

	}
	


	@SuppressWarnings("unchecked")
	private void setPropertyDataSource(){
		
		record.getItemProperty( "newUsername" ).setValue( "" );
		record.getItemProperty( "password" ).setValue( "" );
		record.getItemProperty( "newPassword" ).setValue( "" );
		
		// Username field
		UsernameTFValidator usernameTFValidator = new UsernameTFValidator( "Field equired in valid format" );
		usernameTFValidator.init(lbNormalMsg, lbErrorMsg,"" );
		this.tFNewUsername.addValidator( usernameTFValidator );
		//this.tFNewUsername.setInvalidCommitted( true );
		this.tFNewUsername.setPropertyDataSource( record.getItemProperty( "newUsername" ) );
		
		// Required fields
		RequiredTFValidator rTFValidator = new RequiredTFValidator( "Field required in valid format" );
		this.tFNewPass.addValidator(rTFValidator );
		this.tFCurrentPass.addValidator(rTFValidator );
		
		this.tFNewPass.setPropertyDataSource( record.getItemProperty( "newPassword" ) );
		this.tFCurrentPass.setPropertyDataSource( record.getItemProperty( "password" )  );
		
		
		// Password fields
		PasswordTFValidator passValidator = new PasswordTFValidator( "Field required. Password and Confirm Password fields should match." );
		passValidator.init(lbNormalMsg, lbErrorMsg, record.getItemProperty( "newPassword" ) );
		
		this.tFConfirmNewPass.addValidator( passValidator );
		this.tFConfirmNewPass.setPropertyDataSource( record.getItemProperty( "confirmPass" ) );
		
		
		
	}
	
	private void format(){
		
		
	}
	

	
	
	private void showPopup() {
		processingPopup.setContent(this);
		processingPopup.center();
		processingPopup.setClosable( true );
		processingPopup.setEnabled(true);
		processingPopup.setModal(true);
		processingPopup.setDraggable(false);
		processingPopup.setResizable(false);
		processingPopup.setSizeUndefined();
		UI.getCurrent().addWindow(processingPopup);
	}


	private void setContent() {
		chkUsername.setValue( false );
		chkPass.setValue( false );
		
		
		if(record != null  ) {
			showPopup();
			format();
		} else {
			
			Notification.show("Oops... error loading data. Please  try again.",
					Notification.Type.ERROR_MESSAGE);
		}
	}
	
	private void setAuth( InUserDetails inData ){
		
		inData.setUsername( UI.getCurrent().getSession().getAttribute( DLoginUIController.USERNAME ).toString() );
		inData.setUserSession(  UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR ).toString()  );

		
	}
	
	private void setSettingsAuth( InSettings inData ){
		
		inData.setUsername( UI.getCurrent().getSession().getAttribute( DLoginUIController.USERNAME ).toString() );
		inData.setUserSession(  UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR ).toString()  );

		
	}
	
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}

}
