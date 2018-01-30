package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class ExportLedger  implements Serializable {
	
	
	private static final long serialVersionUID = 7431320759077668024L;
	// private static Logger log = LogManager.getLogger();
	
	private String accNo, name,  amount, date;
	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	

}
