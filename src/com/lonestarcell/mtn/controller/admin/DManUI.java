package com.lonestarcell.mtn.controller.admin;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DManUI implements DUIInitializable {

	protected DManUIController duic;

	public DManUI() {
		System.out.println("DManUIController constructor called.");
		init(new DManUIController());
	}

	@Override
	public void setHeader() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContent() {
		UI.getCurrent().setContent(duic);

	}

	@Override
	public void swap(DManUIController duic, Component cuid) {
		if (duic == null) {
			System.err.println("Management NULL things.");
			return;
		}

		Component c = duic.getComponent(1);
		VerticalLayout v1 = (VerticalLayout) c;
		HorizontalLayout h1 = (HorizontalLayout) v1.getComponent(0);
		v1 = (VerticalLayout) h1.getComponent(1);
		Panel p1 = (Panel) v1.getComponent(0);
		v1 = (VerticalLayout) p1.getContent();
		v1.replaceComponent(v1.getComponent(0), cuid);
		v1.setExpandRatio(cuid, 1);
		v1.setWidth("100%");
		// v1.addStyleName("sn-p");
		// cuid.addStyleName("sn-c");

		System.out.println("UI swapping successful");

	}

	@Override
	public void init(DManUIController duic) {
		setDUI(duic);
		setHeader();
		setContent();
		setFooter();
		new DDashboardUI(duic);

	}

	@Override
	public void setFooter() {
		
		

	}

	@Override
	public DManUIController getDUI() {
		return duic;
	}

	@Override
	public void setDUI(DManUIController duic) {
		this.duic = duic;
	}

}
