package com.lonestarcell.mtn.design.main;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
public class DUIDesign extends VerticalLayout {
	protected VerticalLayout cHeader;
	protected Button btnLoginHome;
	protected VerticalLayout logoArea;
	protected Panel cMidContent;
	protected VerticalLayout cPay;
	protected VerticalLayout cFooter;
	protected HorizontalLayout cFooterInner;
	protected HorizontalLayout cOwnership;
	protected Label lbCRYearStart;
	protected Label lbYearSeparator;
	protected Label lbCRYearCurrent;
	protected Label lbClient;
	protected HorizontalLayout cFooterRight;

	public DUIDesign() {
		Design.read(this);
	}
}