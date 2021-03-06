package com.lonestarcell.mtn.design.admin;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class DProfileFormUIDesign extends VerticalLayout {
	protected VerticalLayout cMMRightContent;
	protected HorizontalLayout mmPanelHead2;
	protected Panel mmPanel2;
	protected VerticalLayout cBookingDetails;
	protected HorizontalLayout cBDHead;
	protected HorizontalLayout cBDBody;
	protected Label lbUN;
	protected Label lbPass;
	protected Label lbFullN;
	protected Label lbCreatedBy;
	protected Label lbDateCreated;
	protected Label lbRole;
	protected Label lbLastLogin;
	protected Button btnChangeProfile;
	protected VerticalLayout cEditProfileForm;
	protected Label lbErrorMsg;
	protected Label lbNormalMsg;
	protected CheckBox chkUsername;
	protected CheckBox chkPass;
	protected TextField tFNewUsername;
	protected PasswordField tFCurrentPass;
	protected PasswordField tFNewPass;
	protected PasswordField tFConfirmNewPass;
	protected HorizontalLayout cControls;
	protected Button btnCancel;
	protected Button btnSave;

	public DProfileFormUIDesign() {
		Design.read(this);
	}
}
