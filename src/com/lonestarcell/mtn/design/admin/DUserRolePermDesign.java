package com.lonestarcell.mtn.design.admin;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
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
public class DUserRolePermDesign extends VerticalLayout {
	protected Tree tPerm;
	protected Button btnEdit;
	protected Button btnDisable;
	protected Button btnActivate;
	protected Button btnDelete;

	public DUserRolePermDesign() {
		Design.read(this);
	}
}
