package com.lonestarcell.mtn.design.admin;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
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
public class DDateFilterUIDesign extends HorizontalLayout {
	protected HorizontalLayout cLeftDateFilter;
	protected HorizontalLayout cDateFilters;
	protected PopupDateField dFStartDate;
	protected PopupDateField dFLastDate;
	protected Button btnFilter;
	protected Button btnRefresh;
	protected Button btnClearFilters;
	protected HorizontalLayout cDataExport;
	protected Button btnExport;
	protected Button btnExportOps;
	protected VerticalLayout moreDropDown;
	protected Button btnExportOps1;
	protected Button btnExportOps11;
	protected Label lbTotalRecords;
	protected FormLayout cRevenue;
	protected Label lbTotalRevenue;
	protected HorizontalLayout cPagination;
	protected Button btnPagePrev;
	protected Button btnPageAfterPrev;
	protected Button btnPageBeforeNext;
	protected Button btnPageNext;

	public DDateFilterUIDesign() {
		Design.read(this);
	}
}
