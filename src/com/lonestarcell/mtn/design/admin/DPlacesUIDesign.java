package com.lonestarcell.mtn.design.admin;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
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
 * e.g class LoginView extends LoginDesign implements View { � }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class DPlacesUIDesign extends VerticalLayout {
	protected VerticalLayout cMMRightContent;
	protected HorizontalLayout mmPanelHead2;
	protected Panel mmPanel2;
	protected VerticalLayout cGridParent;
	protected HorizontalLayout cBDHead;
	protected HorizontalLayout cAddNewPlace;
	protected Button btnNewPlace;
	protected VerticalLayout cGridPlaces;
	protected HorizontalLayout cBDBody;
	protected VerticalLayout cPlaceForm;
	protected Label lbErrorMsg;
	protected Label lbNormalMsg;
	protected TextField tFNewPlaceName;
	protected PasswordField tFNewPlaceAbbrv;
	protected HorizontalLayout cControls;
	protected Button btnCancel;
	protected Button btnSave;

	public DPlacesUIDesign() {
		Design.read(this);
	}
}