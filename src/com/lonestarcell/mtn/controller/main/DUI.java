package com.lonestarcell.mtn.controller.main;

import java.util.Calendar;

import com.lonestarcell.mtn.design.main.DUIDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DUI extends DUIDesign implements View, DUIInitializable,
		DUIControllable {

	private static final long serialVersionUID = -4025626441000922036L;
	protected DUI duic;

	@Override
	public void setHeader() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContent() {

	}

	@Override
	public void setFooter() {
String startYear = "2017";
		
		Calendar cal = Calendar.getInstance();
		String currentYear = cal.get( Calendar.YEAR )+"";
		
		
		this.lbClient.setValue( "&nbspMTN Benin,&nbsp" );
		this.lbCRYearStart.setValue( startYear );
		this.lbCRYearCurrent.setValue( currentYear );
		
		if( startYear.equals( currentYear )) {
			
			this.lbCRYearCurrent.setVisible(false);
			this.lbYearSeparator.setVisible(false);
			
		}
		

	}

	@Override
	public void swap(DUI duic, Component cuid) {
		if (duic == null) {
			System.err.println("Null thingsx.");
			return;
		}

		Component c = duic.getComponent( 1 );
		Panel p = ( Panel ) c;
		VerticalLayout v = ( VerticalLayout ) p.getContent();
		v = ( VerticalLayout ) v.getComponent( 0 );
		v.replaceComponent(v.getComponent( 0 ), cuid);

	}

	@Override
	public void init(DUI duic) {

		setHeader();
		setContent();
		setFooter();
		attachCommandListeners();

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachCommandListeners() {

		attachLogin();
	}

	private void attachLogin() {
		this.btnLoginHome.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getSession()
						.setAttribute(DLoginUIController.USERNAME, null);
				UI.getCurrent().getSession()
						.setAttribute(DLoginUIController.SESSION_VAR, null);
				UI.getCurrent().getSession()
						.setAttribute(DLoginUIController.PROFILE_ID, null);
				UI.getCurrent().getNavigator().navigateTo("login");

			}

		});
	}

	@Override
	public void setUIState() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {

	}

}
