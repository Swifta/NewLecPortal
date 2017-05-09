package com.lonestarcell.mtn.controller.util;

import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.vaadin.data.validator.AbstractStringValidator;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class UserTFUtil extends AbstractStringValidator{

	protected boolean isInitCalled = false;
	protected Label lbNormalMsg;
	protected Label lbErrorMsg;
	protected String newValue = "";
	
	private static final long serialVersionUID = 1L;

	public UserTFUtil(String errorMessage) {
		super(errorMessage);
	}
	
	public void init( Label lbNormal, Label lbError, String newEmail ){
		this.lbErrorMsg = lbError;
		this.lbNormalMsg = lbNormal;
		this.newValue = newEmail;
		isInitCalled = true;
	}

	protected void setAuth( InUserDetails inData ){
		inData.setUsername( UI.getCurrent().getSession().getAttribute( DLoginUIController.USERNAME ).toString() );
		inData.setUserSession(  UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR ).toString()  );	
	}

	@Override
	protected boolean isValidValue(String value) {
		return false;
	}
	
	protected long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	protected String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}

}
