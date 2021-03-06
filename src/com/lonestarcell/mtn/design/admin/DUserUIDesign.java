package com.lonestarcell.mtn.design.admin;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
public class DUserUIDesign extends HorizontalLayout {
	protected Panel leftBar;
	protected Button btnUsers;
	protected Button btnNewUser;
	protected VerticalLayout cForms;
	protected VerticalLayout cBookingDetails3;
	protected HorizontalLayout cBDHead3;
	protected Label lbFormHeader;
	protected VerticalLayout cMobileNumber3;
	protected TextField tFUN;
	protected TextField tFFN;
	protected TextField tFLN;
	protected TextField tFSN;
	protected TextField tFEmail;
	protected ComboBox comboProfile;
	protected CheckBox chkStatus;
	protected HorizontalLayout cControls3;
	protected Button btnCancel;
	protected Button btnSave;

	public DUserUIDesign() {
		Design.read(this);
	}
}
