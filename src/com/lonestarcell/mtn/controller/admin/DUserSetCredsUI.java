package com.lonestarcell.mtn.controller.admin;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.RequiredTFValidator;
import com.lonestarcell.mtn.design.admin.DSetCredsUIDesign;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.model.admin.MUtil;
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

public class DUserSetCredsUI extends DSetCredsUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private Logger log = LogManager.getLogger( DUserSetCredsUI.class.getName() );
	private Item record;
	

	public DUserSetCredsUI( Item record) {
		this.setRecord( record );
		init(null);
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
		this.attachBtnGenNewPass();
		this.attachBtnSave();
	}
	
	
	
	private void attachBtnSave(){
		this.btnSave.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				try {
					if( isFormValid() ){
						
						Out out = resetUserPassAdmin();
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
					} catch ( Exception e ){
						
						Notification.show( "Error occured. Please try again / Contact support.",
								Notification.Type.ERROR_MESSAGE );
						lbErrorMsg.removeStyleName("sn-display-none");
						lbNormalMsg.addStyleName("sn-display-none");
						lbErrorMsg.setValue( "Error occured. Please try again / Contact support." );
						
					}
				
				
			}
			
		});
	}
	
	
	private Out resetUserPassAdmin(){
		MUserDetails mUserDetails = new MUserDetails(  getCurrentUserId(), getCurrentUserSession()  );
		InUserDetails inData = new InUserDetails();
		setAuth( inData );
		inData.setRecord( record );
	
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = mUserDetails.resetUserPassAdmin( in );
		
		return out;
		
	}
	
	
	private void attachBtnGenNewPass(){
		this.btnGenNewPass.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {
				
				tFNewPassword.setReadOnly( false );
				record.getItemProperty( "newPassword" ).setValue( MUtil.genNewPass() );
				tFNewPassword.setReadOnly( true );
				
				
			}
			
		});
	}
	
	
	
	
	private boolean isFormValid(){
		
		if( !this.isTFValid( this.tFNewUsername ) )
			return false;
		if( !this.isTFValid( this.tFNewPassword ) )
			return false;
		return true;
	}
	
	private boolean isTFValid (TextField tF){
		
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
	

	
	private void attachBtnCancel(){
		this.btnCancel.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				processingPopup.close();
			}
			
		});
	}
	
	private void attachOnClose(){
		processingPopup.addCloseListener( new CloseListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				refreshRecord();
				new DUserDetailsUI( record );
				
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
			
			MUserDetails mUserDetails = new MUserDetails( getCurrentUserId(), getCurrentUserSession() );
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

	@Override
	public void init(DManUIController duic) {
	
		this.setProcessingPopup( new Window("User Details") );
		attachCommandListeners();
		setPropertyDataSource();
		setContent();
		

	}
	


	@SuppressWarnings("unchecked")
	private void setPropertyDataSource(){
		
		lbNormalMsg.removeStyleName("sn-display-none");
		lbErrorMsg.addStyleName("sn-display-none");
		
		record.getItemProperty( "newUsername" ).setValue( record.getItemProperty( "username" ).getValue() );
		record.getItemProperty( "newPassword" ).setValue( MUtil.genNewPass() );
		
		this.tFNewUsername.setPropertyDataSource( record.getItemProperty( "newUsername" ) );
		this.tFNewPassword.setPropertyDataSource( record.getItemProperty( "newPassword" ) );
		
		this.tFNewUsername.addValidator( new RequiredTFValidator( "" ) );
		this.tFNewPassword.addValidator( new RequiredTFValidator( "" ) );
		
		
		
		this.tFNewUsername.setReadOnly( true );
		this.tFNewPassword.setReadOnly( true );
		
	

		
	}
	
	private void format(){
		
		
	}
	

	
	
	private void showPopup() {
		processingPopup.setContent(this);
		processingPopup.center();
		processingPopup.setClosable(false);
		processingPopup.setEnabled(true);
		processingPopup.setModal(true);
		processingPopup.setDraggable(false);
		processingPopup.setResizable(false);
		processingPopup.setSizeUndefined();
		UI.getCurrent().addWindow(processingPopup);
	}


	private void setContent() {
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
	
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}
	


}
