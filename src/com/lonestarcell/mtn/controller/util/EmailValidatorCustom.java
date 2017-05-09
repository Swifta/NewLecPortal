package com.lonestarcell.mtn.controller.util;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutUserDetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.model.admin.MUserSelfCare;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;


public class EmailValidatorCustom extends EmailValidator{

	private static final long serialVersionUID = 1L;
	
	
	private boolean isInitCalled = false;
	private Label lbNormalMsg;
	private Label lbErrorMsg;
	private String newEmail = "";

	public EmailValidatorCustom(String errorMessage) {
		super(errorMessage);
		
	}
	
	public void init( Label lbNormal, Label lbError, String newEmail ){
		this.lbErrorMsg = lbError;
		this.lbNormalMsg = lbNormal;
		this.newEmail = newEmail;
		isInitCalled = true;
	}
	
	
	
	@Override
	protected boolean isValidValue(String value) {
		
		boolean status = super.isValidValue(value);
		
		if( status ){
			Out out = this.checkEmailUnique( value, newEmail );
			status =  out.getStatusCode() == 1;
			
			if( status ){
				if( isInitCalled ){
					lbNormalMsg.removeStyleName("sn-display-none");
					lbErrorMsg.addStyleName("sn-display-none");
				}
			} else {
				if( isInitCalled ){
					
					lbErrorMsg.removeStyleName("sn-display-none");
					lbNormalMsg.addStyleName("sn-display-none");
					lbErrorMsg.setValue( out.getMsg() );
				}
				this.setErrorMessage( out.getMsg() );
			}
		}
		
		
		
		return status;
		
	}
	
	private Out checkEmailUnique( String email, String newEmail ){
		
		
		
		InUserDetails inData = new InUserDetails();
		setAuth( inData );
		
		OutUserDetails outUserDetails = new OutUserDetails();
		outUserDetails.setEmail( email );
		outUserDetails.setNewEmail( newEmail );
		Item record = new BeanItem<>( outUserDetails );
		
		
		inData.setRecord( record );
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		
		
		if( getCurrentUserProfileId() == 1 ){
			
			return new MUserDetails( getCurrentUserId(), getCurrentUserSession() ).checkEmailUnique( in );
		}else{
			return new MUserSelfCare().checkEmailUnique( in );
			
		}
		
		
	}
	
	
	private void setAuth( InUserDetails inData ){
		
		inData.setUsername( UI.getCurrent().getSession().getAttribute( DLoginUIController.USERNAME ).toString() );
		inData.setUserSession(  UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR ).toString()  );	
	}
	
	
	private int getCurrentUserProfileId( ){
		return ( int )UI.getCurrent().getSession().getAttribute( DLoginUIController.PROFILE_ID );
	}
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}

	
	
	
}
