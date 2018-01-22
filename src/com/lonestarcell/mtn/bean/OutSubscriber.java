package com.lonestarcell.mtn.bean;

public class OutSubscriber extends AbstractDataBean {
	
	private static final long serialVersionUID = 7431320759077668024L;
	private String transactionNumber, type, amount, status, payer, payee, date;
	
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber( String transactionNumber ) {
		this.transactionNumber = transactionNumber;
		this.column1 = transactionNumber;
	}
	
	public String getType(String type) {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
		this.column2 = type;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.column3 = amount;
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.column4 = status;
		this.status = status;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.column5 = payer;
		this.payer = payer;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.column6 = payee;
		this.payee = payee;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.column7 = date;
		this.date = date;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	

}
