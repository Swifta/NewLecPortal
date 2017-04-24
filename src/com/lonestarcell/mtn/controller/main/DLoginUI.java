package com.lonestarcell.mtn.controller.main;

import com.vaadin.ui.UI;

public class DLoginUI extends DUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DLoginUIController dLogin;
	DLoginUI(){
		init();
	}
		

	@Override
	public void setContent() {
		dLogin = new DLoginUIController();
		UI.getCurrent().setContent(dLogin);
	}
	
	@Override
	public void init(){
		setContent();
		setLoginUIC(dLogin);
		setFooter();
		
	}
	
	
	
	public DLoginUIController getLoginUIC(){
		return dLogin;
	}
	
	public void setLoginUIC(DLoginUIController dLogin){
		this.dLogin = dLogin;
	}

	


}
