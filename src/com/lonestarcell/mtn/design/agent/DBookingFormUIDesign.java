package com.lonestarcell.mtn.design.agent;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { � }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class DBookingFormUIDesign extends VerticalLayout {
	protected Panel content3;
	protected VerticalLayout cForms3;
	protected VerticalLayout cBookingDetails5;
	protected HorizontalLayout cBDHead5;
	protected Label lbFormHeader;
	protected VerticalLayout cMobileNumber5;
	protected TextField tFClient;
	protected TextField tFBookingRef;
	protected TextField tFCost;
	protected PopupDateField tFDate;
	protected HorizontalLayout cControls5;
	protected Button btnCancel;
	protected Button btnAdd;

	public DBookingFormUIDesign() {
		Design.read(this);
	}
}
