package com.lonestarcell.mtn.controller.main;

import com.vaadin.ui.VerticalLayout;

public class DPaymentStateUI extends DUI {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String bf = null;
	private String msisdn = null;
	private String amount = null;
	DPaymentStateUI(DUI duic, String bf, String msisdn, String amount){

		
		this.bf = bf;
		this.msisdn = msisdn;
		this.amount = amount;
		init(duic);
	}
	
	@Override
	public void init(DUI duic){
		this.duic = duic;
		setContent();
		
	}
	
	@Override
	public void setContent() {
		swap(duic, new VerticalLayout());
		new DPaymentStateUIController(bf, msisdn, amount);
	}
	
	  
	
	


}
