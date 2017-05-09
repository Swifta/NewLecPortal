package com.lonestarcell.mtn.controller.util;

import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.validator.AbstractStringValidator;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class UserPasswordTFUtil extends AbstractStringValidator{

	protected boolean isInitCalled = false;
	protected Label lbNormalMsg;
	protected Label lbErrorMsg;
	protected Property<String> otherFieldDS = new ObjectProperty<String>( "", String.class );
	
	private static final long serialVersionUID = 1L;

	public UserPasswordTFUtil(String errorMessage) {
		super(errorMessage);
	}
	
	public void init( Label lbNormal, Label lbError, Property<String> otherFieldDS ){
		this.lbErrorMsg = lbError;
		this.lbNormalMsg = lbNormal;
		this.otherFieldDS = otherFieldDS;
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
}
