package com.lonestarcell.mtn.design.admin;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
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
public class DEditCredsUIDesign extends VerticalLayout {
	protected VerticalLayout cMMRightContent;
	protected HorizontalLayout mmPanelHead2;
	protected Panel mmPanel2;
	protected VerticalLayout cEditProfileForm;
	protected Label lbNormalMsg;
	protected Label lbErrorMsg;
	protected VerticalLayout cUsernamePassword;
	protected CheckBox chkUsername;
	protected CheckBox chkPass;
	protected CheckBox chkProfile;
	protected TextField tFNewUsername;
	protected PasswordField tFCurrentPass;
	protected PasswordField tFNewPass;
	protected PasswordField tFConfirmNewPass;
	protected ComboBox comboProfile;
	protected HorizontalLayout cControls;
	protected Button btnCancel;
	protected Button btnSave;

	public DEditCredsUIDesign() {
		Design.read(this);
	}
}
