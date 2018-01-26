package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class OutLedger extends AbstractDataBean implements Serializable {
	
	
	private static final long serialVersionUID = 7431320759077668024L;
	// private static Logger log = LogManager.getLogger();
	
	private String accNo, name,  amount, date;
	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.column1 = accNo;
		this.accNo = accNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.column2 = name;
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.column3 = amount;
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.column4 = date;
		this.date = date;
	}
	
	
	

}
