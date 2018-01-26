package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class OutMerchant extends AbstractDataBean implements Serializable {
	
	private static final long serialVersionUID = 7431320759077668024L;
	private String name, msisdn,  tno, type, amount,  status, channel, desc, payee, payer, entryDate ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.column1 = name;
		this.name = name;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.column2 = msisdn;
		this.msisdn = msisdn;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.column3 = tno;
		this.tno = tno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.column4 = type;
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.column5 = amount;
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.column6 = status;
		this.status = status;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.column7 = channel;
		this.channel = channel;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.column8 = desc;
		this.desc = desc;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.column9 = payee;
		this.payee = payee;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.column10 = payer;
		this.payer = payer;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		super.date = entryDate;
		this.entryDate = entryDate;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	

}
