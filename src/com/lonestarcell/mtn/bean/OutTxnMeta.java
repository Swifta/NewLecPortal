package com.lonestarcell.mtn.bean;

import java.io.Serializable;

import com.vaadin.data.Property;

public class OutTxnMeta implements Serializable{
	
	private static final long serialVersionUID = -8474942313392257605L;
	private Property< String > totalRecord, totalRevenue;
	private Long totalTxnFail, totalTxnSuccess, totalTokenFail, totalTokenSuccess, totalInfoFail, totalInfoSuccess, totalSMSFail, totalSMSSuccess;
	private String sTotalTxnFail, sTotalTxnSuccess, sTotalTokenFail, sTotalTokenSuccess, sTotalInfoFail, sTotalInfoSuccess, sTotalSMSFail, sTotalSMSSuccess;
	
	
	private String sTotalInfo, sTotalToken, sTotalSMS, sTotalTxn;
	
	public Property<String> getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Property<String> totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Property<String> getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(Property<String> totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public Long getTotalTxnFail() {
		return totalTxnFail;
	}
	public void setTotalTxnFail(Long totalTxnFail) {
		this.totalTxnFail = totalTxnFail;
	}
	public Long getTotalTxnSuccess() {
		return totalTxnSuccess;
	}
	public void setTotalTxnSuccess(Long totalTxnSuccess) {
		this.totalTxnSuccess = totalTxnSuccess;
	}
	public Long getTotalTokenFail() {
		return totalTokenFail;
	}
	public void setTotalTokenFail(Long totalTokenFail) {
		this.totalTokenFail = totalTokenFail;
	}
	public Long getTotalTokenSuccess() {
		return totalTokenSuccess;
	}
	public void setTotalTokenSuccess(Long totalTokenSuccess) {
		this.totalTokenSuccess = totalTokenSuccess;
	}
	public Long getTotalInfoFail() {
		return totalInfoFail;
	}
	public void setTotalInfoFail(Long totalInfoFail) {
		this.totalInfoFail = totalInfoFail;
	}
	public Long getTotalInfoSuccess() {
		return totalInfoSuccess;
	}
	public void setTotalInfoSuccess(Long totalInfoSuccess) {
		this.totalInfoSuccess = totalInfoSuccess;
	}
	public Long getTotalSMSFail() {
		return totalSMSFail;
	}
	public void setTotalSMSFail(Long totalSMSFail) {
		this.totalSMSFail = totalSMSFail;
	}
	public Long getTotalSMSSuccess() {
		return totalSMSSuccess;
	}
	public void setTotalSMSSuccess(Long totalSMSSuccess) {
		this.totalSMSSuccess = totalSMSSuccess;
	}
	public String getsTotalTxnFail() {
		return sTotalTxnFail;
	}
	public void setsTotalTxnFail(String sTotalTxnFail) {
		this.sTotalTxnFail = sTotalTxnFail;
	}
	public String getsTotalTxnSuccess() {
		return sTotalTxnSuccess;
	}
	public void setsTotalTxnSuccess(String sTotalTxnSuccess) {
		this.sTotalTxnSuccess = sTotalTxnSuccess;
	}
	public String getsTotalTokenFail() {
		return sTotalTokenFail;
	}
	public void setsTotalTokenFail(String sTotalTokenFail) {
		this.sTotalTokenFail = sTotalTokenFail;
	}
	public String getsTotalTokenSuccess() {
		return sTotalTokenSuccess;
	}
	public void setsTotalTokenSuccess(String sTotalTokenSuccess) {
		this.sTotalTokenSuccess = sTotalTokenSuccess;
	}
	public String getsTotalInfoFail() {
		return sTotalInfoFail;
	}
	public void setsTotalInfoFail(String sTotalInfoFail) {
		this.sTotalInfoFail = sTotalInfoFail;
	}
	public String getsTotalInfoSuccess() {
		return sTotalInfoSuccess;
	}
	public void setsTotalInfoSuccess(String sTotalInfoSuccess) {
		this.sTotalInfoSuccess = sTotalInfoSuccess;
	}
	public String getsTotalSMSFail() {
		return sTotalSMSFail;
	}
	public void setsTotalSMSFail(String sTotalSMSFail) {
		this.sTotalSMSFail = sTotalSMSFail;
	}
	public String getsTotalSMSSuccess() {
		return sTotalSMSSuccess;
	}
	public void setsTotalSMSSuccess(String sTotalSMSSuccess) {
		this.sTotalSMSSuccess = sTotalSMSSuccess;
	}
	public Long getTotalInfo() {
		return this.getTotalInfoFail()+ this.getTotalInfoSuccess();
	}
	
	public Long getTotalToken() {
		return this.getTotalTokenFail()+ this.getTotalTokenSuccess();
	}
	
	public Long getTotalSMS() {
		return this.getTotalSMSFail()+ this.getTotalSMSSuccess();
	}
	
	public Long getTotalTxn() {
		return this.getTotalTxnFail() + this.getTotalTxnSuccess();
	}
	
	public String getsTotalInfo() {
		return sTotalInfo;
	}
	public void setsTotalInfo(String sFotalInfo) {
		this.sTotalInfo = sFotalInfo;
	}
	public String getsTotalToken() {
		return sTotalToken;
	}
	public void setsTotalToken(String sTotalToken) {
		this.sTotalToken = sTotalToken;
	}
	public String getsTotalSMS() {
		return sTotalSMS;
	}
	public void setsTotalSMS(String sTotalSMS) {
		this.sTotalSMS = sTotalSMS;
	}
	public String getsTotalTxn() {
		return sTotalTxn;
	}
	public void setsTotalTxn(String sTotalTxn) {
		this.sTotalTxn = sTotalTxn;
	}
	public Long getPerSMS() {
		return ( long ) ( ( ( this.getTotalSMSSuccess() / ( double ) this.getTotalSMS() )*100 )  );
	}
	
	public Long getPerTxn() {
		return ( long ) ( ( ( this.getTotalTxnSuccess() / ( double ) this.getTotalTxn() )*100 )  );
	}
	
	public Long getPerToken() {
		return  ( long ) ( ( ( this.getTotalTokenSuccess() / ( double ) this.getTotalToken() )*100 )  );
	}
	
	public Long getPerInfo() {
		return ( long ) ( ( ( this.getTotalInfoSuccess() / ( double ) this.getTotalInfo() )*100 )  );
	}
	

	
	
	
	
	
	
	
	

}
