package com.lonestarcell.mtn.controller.admin;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.EmailValidatorCustom;
import com.lonestarcell.mtn.controller.util.RequiredTFValidator;
import com.lonestarcell.mtn.controller.util.TFValidator;
import com.lonestarcell.mtn.design.admin.DEditPersonalInfoUIDesign;
import com.lonestarcell.mtn.model.admin.MUserSelfCare;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class DUserEditPersonalInfoUI extends DEditPersonalInfoUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private Logger log = LogManager.getLogger( DUserEditPersonalInfoUI.class.getName() );
	private Item record;
	private MUserSelfCare mUserDetails;
	private ApplicationContext springAppContext;

	public DUserEditPersonalInfoUI( Item record, ApplicationContext springAppContext ) {
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





	public MUserSelfCare getmUserDetails() {
		return mUserDetails;
	}





	public void setmUserDetails(MUserSelfCare mUserDetails) {
		this.mUserDetails = mUserDetails;
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
		this.attachBtnSave();
	}
	
	
	private void attachBtnSave(){
		this.btnSave.addClickListener( new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					if( isFormValid() ){
						Out out = updatePersonalInfo();
						if( out.getStatusCode() == 1 ){
							Notification.show( out.getMsg(),
									Notification.Type.HUMANIZED_MESSAGE );
							processingPopup.close();
						}else{
							Notification.show( out.getMsg(),
									Notification.Type.ERROR_MESSAGE );
							lbErrorMsg.removeStyleName("sn-display-none");
							lbNormalMsg.addStyleName("sn-display-none");
							lbErrorMsg.setValue( out.getMsg() );
						}
						
					}
				
				} catch( Exception ex ){
					
					Notification.show( "Error occured. Please try again / Contact support",
							Notification.Type.ERROR_MESSAGE );
					lbErrorMsg.removeStyleName("sn-display-none");
					lbNormalMsg.addStyleName("sn-display-none");
					lbErrorMsg.setValue( "Error occured. Please try again / Contact support"  );
					ex.printStackTrace();
					
				}
				
			}});
	}
	
	
	@SuppressWarnings("unchecked")
	private Out updatePersonalInfo(){
		MUserSelfCare mUserDetails = new MUserSelfCare();
		
		InUserDetails inData = new InUserDetails();
		setAuth( inData );
		record.getItemProperty( "email" ).setValue( record.getItemProperty( "newEmail" ).getValue() );
		
		inData.setRecord( record );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = mUserDetails.updateUserPersonalInfo( in );
		
		return out;
		
	}
	
	
	private boolean isFormValid(){
		
		if( !this.isRequiredTFValid( this.tFNewFirstName ) )
			return false;
		if( !this.isRequiredTFValid( this.tFNewLastName ) )
			return false;
		if( !this.isTFValid( this.tFNewSurname ) )
			return false;
		
		return this.tFNewEmail.isValid();
	}
	
	private boolean isRequiredTFValid (TextField tF){
		
		if( !tF.isValid() ){
			
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				RequiredTFValidator v = (RequiredTFValidator) itr.next();
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
	
	
	private boolean isTFValid (TextField tF){
		
		if( !tF.isValid() ){
			
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				TFValidator v = (TFValidator) itr.next();
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
	
	
	private void attachOnClose(){
		processingPopup.addCloseListener( new CloseListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				refreshRecord();
				new DUserDetailsUI( record, springAppContext );
				
			}
			
		});
	}
	
	private void attachBtnCancel(){
		this.btnCancel.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				processingPopup.close();
				
			}
			
		});
	}
	
	public boolean refreshRecord(){
		
		String username = UI.getCurrent().getSession()
		.getAttribute(DLoginUIController.USERNAME).toString();
		
		InUserDetails inData = new InUserDetails();
		inData.setUsername( username );
		inData.setRecord( record );
		
		this.setAuth( inData );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		if( record != null ) {
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

	private void init( ) {
		this.setProcessingPopup( new Window("User Details") );
		this.setmUserDetails( new MUserSelfCare() );
		attachCommandListeners();
		setPropertyDataSource();
		setContent();
		

	}
	


	@SuppressWarnings("unchecked")
	private void setPropertyDataSource(){
		
		
		
		this.tFNewFirstName.setPropertyDataSource( record.getItemProperty( "newFirstName" ) );
		this.tFNewLastName.setPropertyDataSource( record.getItemProperty( "newLastName" ) );
		this.tFNewSurname.setPropertyDataSource( record.getItemProperty( "newSurname" ) );
		this.tFNewEmail.setPropertyDataSource( record.getItemProperty( "newEmail" ) );
		
		// Email
		EmailValidatorCustom emailValidator = new EmailValidatorCustom( "Correct email format. [ i.e. example@domain.com ]" );
		emailValidator.init(lbNormalMsg, lbErrorMsg, record.getItemProperty( "email" ).getValue().toString() );
		this.tFNewEmail.addValidator( emailValidator );
		
		// Required fields
		RequiredTFValidator rTFValidator = new RequiredTFValidator( "Field required in valid format" );
		this.tFNewFirstName.addValidator( rTFValidator );
		this.tFNewLastName.addValidator( rTFValidator );
		
		// Optional fields
		TFValidator tFValidator = new TFValidator( "Field required in valid format" );
		// this.tFNewSurname.addValidator( tFValidator );
		
		tFNewSurname.setVisible( false );
		
		
		record.getItemProperty( "newFirstName" ).setValue( record.getItemProperty( "firstName" ).getValue() );
		record.getItemProperty( "newLastName" ).setValue( record.getItemProperty( "lastName" ).getValue() );
		
		Object surname = record.getItemProperty( "surname" ).getValue();
		if( surname != null && surname.equals( "N/A" )){
			record.getItemProperty( "newSurname" ).setValue( "" );
		} else {
			record.getItemProperty( "newSurname" ).setValue(surname );
		}
		record.getItemProperty( "newEmail" ).setValue( record.getItemProperty( "email" ).getValue() );

		log.debug( "Data sources set successfully." );
		
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
		if( record != null ) {
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

}
