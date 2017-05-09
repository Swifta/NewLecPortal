package com.lonestarcell.mtn.bean;

import java.io.Serializable;

import com.vaadin.data.Property;

public class OutTxnDetails implements Serializable{
	
	private static final long serialVersionUID = -8474942313392257605L;
	private Property< String > newFxValue, newFxCreator, fxId, fxValue, fxCreator, fxTimestamp, currency, rateId, rate,  amount, itronAmount, smsDateCreate, smsDateEnd, verifDateEnd,
	verifDateCreate, tokenDateEnd, tokenDateCreate,txnDateCreate, txnDateEnd, smsStatus, verifStatus,tokenStatus, units, tokenId, sessionVar, mmoId, verifId, smsId, meterNo, msisdn, token, reqCurrency, payStatus, swiftaId;

	public Property<String> getCurrency() {
		return currency;
	}

	public void setCurrency(Property<String> currency) {
		this.currency = currency;
	}

	public Property<String> getRateId() {
		return rateId;
	}

	public void setRateId(Property<String> rateId) {
		this.rateId = rateId;
	}

	public Property<String> getRate() {
		return rate;
	}

	public void setRate(Property<String> rate) {
		this.rate = rate;
	}

	public Property<String> getAmount() {
		return amount;
	}

	public void setAmount(Property<String> amount) {
		this.amount = amount;
	}

	public Property<String> getItronAmount() {
		return itronAmount;
	}

	public void setItronAmount(Property<String> itronAmount) {
		this.itronAmount = itronAmount;
	}

	public Property<String> getSmsDateCreate() {
		return smsDateCreate;
	}

	public void setSmsDateCreate(Property<String> smsDateCreate) {
		this.smsDateCreate = smsDateCreate;
	}

	public Property<String> getSmsDateEnd() {
		return smsDateEnd;
	}

	public void setSmsDateEnd(Property<String> smsDateEnd) {
		this.smsDateEnd = smsDateEnd;
	}

	public Property<String> getVerifDateEnd() {
		return verifDateEnd;
	}

	public void setVerifDateEnd(Property<String> verifDateEnd) {
		this.verifDateEnd = verifDateEnd;
	}

	public Property<String> getVerifDateCreate() {
		return verifDateCreate;
	}

	public void setVerifDateCreate(Property<String> verifDateCreate) {
		this.verifDateCreate = verifDateCreate;
	}

	public Property<String> getTokenDateEnd() {
		return tokenDateEnd;
	}

	public void setTokenDateEnd(Property<String> tokenDateEnd) {
		this.tokenDateEnd = tokenDateEnd;
	}

	public Property<String> getTokenDateCreate() {
		return tokenDateCreate;
	}

	public void setTokenDateCreate(Property<String> tokenDateCreate) {
		this.tokenDateCreate = tokenDateCreate;
	}

	public Property<String> getTxnDateCreate() {
		return txnDateCreate;
	}

	public void setTxnDateCreate(Property<String> txnDateCreate) {
		this.txnDateCreate = txnDateCreate;
	}

	public Property<String> getTxnDateEnd() {
		return txnDateEnd;
	}

	public void setTxnDateEnd(Property<String> txnDateEnd) {
		this.txnDateEnd = txnDateEnd;
	}

	public Property<String> getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(Property<String> smsStatus) {
		this.smsStatus = smsStatus;
	}

	public Property<String> getVerifStatus() {
		return verifStatus;
	}

	public void setVerifStatus(Property<String> verifStatus) {
		this.verifStatus = verifStatus;
	}

	public Property<String> getTokenStatus() {
		return tokenStatus;
	}

	public void setTokenStatus(Property<String> tokenStatus) {
		this.tokenStatus = tokenStatus;
	}

	public Property<String> getUnits() {
		return units;
	}

	public void setUnits(Property<String> units) {
		this.units = units;
	}

	public Property<String> getTokenId() {
		return tokenId;
	}

	public void setTokenId(Property<String> tokenId) {
		this.tokenId = tokenId;
	}

	public Property<String> getSessionVar() {
		return sessionVar;
	}

	public void setSessionVar(Property<String> sessionVar) {
		this.sessionVar = sessionVar;
	}

	public Property<String> getMmoId() {
		return mmoId;
	}

	public void setMmoId(Property<String> mmoId) {
		this.mmoId = mmoId;
	}

	public Property<String> getVerifId() {
		return verifId;
	}

	public void setVerifId(Property<String> verifId) {
		this.verifId = verifId;
	}

	public Property<String> getSmsId() {
		return smsId;
	}

	public void setSmsId(Property<String> smsId) {
		this.smsId = smsId;
	}

	public Property<String> getMeterNo() {
		return meterNo;
	}

	public void setMeterNo(Property<String> meterNo) {
		this.meterNo = meterNo;
	}

	public Property<String> getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(Property<String> msisdn) {
		this.msisdn = msisdn;
	}

	public Property<String> getToken() {
		return token;
	}

	public void setToken(Property<String> token) {
		this.token = token;
	}

	public Property<String> getReqCurrency() {
		return reqCurrency;
	}

	public void setReqCurrency(Property<String> reqCurrency) {
		this.reqCurrency = reqCurrency;
	}

	public Property<String> getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Property<String> payStatus) {
		this.payStatus = payStatus;
	}

	public Property<String> getSwiftaId() {
		return swiftaId;
	}

	public void setSwiftaId(Property<String> swiftaId) {
		this.swiftaId = swiftaId;
	}

	public Property<String> getFxValue() {
		return fxValue;
	}

	public void setFxValue(Property<String> fxValue) {
		this.fxValue = fxValue;
	}

	public Property<String> getFxCreator() {
		return fxCreator;
	}

	public void setFxCreator(Property<String> fxCreator) {
		this.fxCreator = fxCreator;
	}

	public Property<String> getFxTimestamp() {
		return fxTimestamp;
	}

	public void setFxTimestamp(Property<String> fxTimestamp) {
		this.fxTimestamp = fxTimestamp;
	}

	public Property<String> getFxId() {
		return fxId;
	}

	public void setFxId(Property<String> fxId) {
		this.fxId = fxId;
	}

	

	public Property<String> getNewFxValue() {
		return newFxValue;
	}

	public void setNewFxValue(Property<String> newFxValue) {
		this.newFxValue = newFxValue;
	}

	public Property<String> getNewFxCreator() {
		return newFxCreator;
	}

	public void setNewFxCreator(Property<String> newFxCreator) {
		this.newFxCreator = newFxCreator;
	}
	
	
	
	

}
