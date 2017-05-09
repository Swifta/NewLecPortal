package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class OutTxn implements Serializable {
	
	private static final long serialVersionUID = 7431320759077668024L;
	private String date, tokenStatus, txnType, itronId, reqCount, sessionVar, mmoId, meterNo, msisdn, token, ReqCurrency, payStatus, statusDesc, swiftaId;
	private int profileId;
	private Double amount, rate;
	public String getSessionVar() {
		return sessionVar;
	}
	public void setSessionVar(String sessionVar) {
		this.sessionVar = sessionVar;
	}

	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getMmoId() {
		return mmoId;
	}
	public void setMmoId(String mmoId) {
		this.mmoId = mmoId;
	}
	public String getMeterNo() {
		return meterNo;
	}
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getReqCurrency() {
		return ReqCurrency;
	}
	public void setReqCurrency(String reqCurrency) {
		ReqCurrency = reqCurrency;
	}
	public String getSwiftaId() {
		return swiftaId;
	}
	public void setSwiftaId(String swiftaId) {
		this.swiftaId = swiftaId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String s) {
		payStatus = s;
	}
	
	
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getTokenStatus() {
		return tokenStatus;
	}
	public void setTokenStatus(String tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getItronId() {
		return itronId;
	}
	public void setItronId(String itronId) {
		this.itronId = itronId;
	}
	public String getReqCount() {
		return reqCount;
	}
	public void setReqCount(String reqCount) {
		this.reqCount = reqCount;
	}
	
	
	
	
	
	
	
	
	
	
	

}
