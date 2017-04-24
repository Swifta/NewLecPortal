package com.lonestarcell.mtn.controller.main;

public enum UICommands {
	
	REGISTER(0),
	LOGIN(1),
	PAYMENT(2),
	BOOKING_REF_CANCEL(3),
	BOOKING_REF_PROCEED(4),
	PAY_DETAILS_BACK(5),
	PAY_DETAILS_CANCEL(6),
	PAY_DETAILS_INIT_PAY(7),
	NONE(8);
	
	
	private int cmd;
	UICommands(int cmd){
		this.cmd = cmd;
	}
	
	public int getCMDId(){
		return this.cmd;
	}
}
