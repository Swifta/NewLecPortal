package com.lonestarcell.mtn.bean;

import java.io.Serializable;

import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class OutTxnMeta implements Serializable{
	
	private static final long serialVersionUID = -8474942313392257605L;
	private Property< String > totalRecord, totalRevenue;
	private Item pgExportLimitItems;
	private Long totalSubSuccess, totalSubOther,totalMerSuccess, totalMerOther, totalTxnSuccess, totalTxnOther,totalUserSuccess, totalUserOther;
	private String sTotalSubSuccess, sTotalSubOther, sTotalSub,sTotalMerSuccess, sTotalMerOther, sTotalMer, sTotalTxnSuccess, sTotalTxnOther, sTotalTxn, sTotalUserSuccess, sTotalUserOther, sTotalUser;
	
	
	
	
	
	
	
	// Subscriber
	public Long getTotalSubSuccess() {
		return totalSubSuccess;
	}
	public void setTotalSubSuccess(Long totalSubSuccess) {
		this.totalSubSuccess = totalSubSuccess;
	}
	public Long getTotalSubOther() {
		return totalSubOther;
	}
	public void setTotalSubOther(Long totalSubOther) {
		this.totalSubOther = totalSubOther;
	}

	
	
	
	// Merchant
	public Long getTotalMerSuccess() {
		return totalMerSuccess;
	}
	public void setTotalMerSuccess(Long totalMerSuccess) {
		this.totalMerSuccess = totalMerSuccess;
	}
	public Long getTotalMerOther() {
		return totalMerOther;
	}
	public void setTotalMerOther(Long totalMerOther) {
		this.totalMerOther = totalMerOther;
	}
	
	
	// Transaction
	public Long getTotalTxnSuccess() {
		return totalTxnSuccess;
	}
	public void setTotalTxnSuccess(Long totalTxnSuccess) {
		this.totalTxnSuccess = totalTxnSuccess;
	}
	public Long getTotalTxnOther() {
		return totalTxnOther;
	}
	public void setTotalTxnOther(Long totalTxnOther) {
		this.totalTxnOther = totalTxnOther;
	}
		
	
	
	
	// User
	public Long getTotalUserSuccess() {
		return totalUserSuccess;
	}
	public void setTotalUserSuccess(Long totalUserSuccess) {
		this.totalUserSuccess = totalUserSuccess;
	}
	public Long getTotalUserOther() {
		return totalUserOther;
	}
	public void setTotalUserOther(Long totalUserOther) {
		this.totalUserOther = totalUserOther;
	}
	
	
	
	
	public Item getPgExportLimitItems() {
		return pgExportLimitItems;
	}
	public void setPgExportLimitItems(Item pgExportLimitItems) {
		this.pgExportLimitItems = pgExportLimitItems;
	}
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
	
	
	
	public Long getTotalSub() {
		return this.getTotalSubOther()+ this.getTotalSubSuccess();
	}
	
	public Long getTotalMer() {
		return this.getTotalMerOther() + this.getTotalMerSuccess();
	}
	
	public Long getTotalTxn() {
		return this.getTotalTxnOther()+ this.getTotalTxnSuccess();
	}
	
	public Long getTotalUser() {
		return this.getTotalUserOther() + this.getTotalUserSuccess();
	}
	
	
	

	public String getsTotalSubSuccess() {
		return sTotalSubSuccess;
	}
	public void setsTotalSubSuccess(String sTotalSubSuccess) {
		this.sTotalSubSuccess = sTotalSubSuccess;
	}
	public String getsTotalSubOther() {
		return sTotalSubOther;
	}
	public void setsTotalSubOther(String sTotalSubOther) {
		this.sTotalSubOther = sTotalSubOther;
	}
	public String getsTotalSub() {
		return sTotalSub;
	}
	public void setsTotalSub(String sTotalSub) {
		this.sTotalSub = sTotalSub;
	}
	public String getsTotalMerSuccess() {
		return sTotalMerSuccess;
	}
	public void setsTotalMerSuccess(String sTotalMerSuccess) {
		this.sTotalMerSuccess = sTotalMerSuccess;
	}
	public String getsTotalMerOther() {
		return sTotalMerOther;
	}
	public void setsTotalMerOther(String sTotalMerOther) {
		this.sTotalMerOther = sTotalMerOther;
	}
	public String getsTotalMer() {
		return sTotalMer;
	}
	public void setsTotalMer(String sTotalMer) {
		this.sTotalMer = sTotalMer;
	}
	public String getsTotalTxnOther() {
		return sTotalTxnOther;
	}
	public void setsTotalTxnOther(String sTotalTxnOther) {
		this.sTotalTxnOther = sTotalTxnOther;
	}
	
	public void setsTotalTxnSuccess(String sTotalTxnSuccess) {
		this.sTotalTxnSuccess = sTotalTxnSuccess;
	}
	public String getsTotalUserSuccess() {
		return sTotalUserSuccess;
	}
	public void setsTotalUserSuccess(String sTotalUserSuccess) {
		this.sTotalUserSuccess = sTotalUserSuccess;
	}
	public String getsTotalUserOther() {
		return sTotalUserOther;
	}
	public void setsTotalUserOther(String sTotalUserOther) {
		this.sTotalUserOther = sTotalUserOther;
	}
	public String getsTotalUser() {
		return sTotalUser;
	}
	
	public String getsTotalTxnSuccess() {
		return sTotalTxnSuccess;
	}
	public void setsTotalUser(String sTotalUser) {
		this.sTotalUser = sTotalUser;
	}
	public String getsTotalTxn() {
		return sTotalTxn;
	}
	public void setsTotalTxn(String sTotalTxn) {
		this.sTotalTxn = sTotalTxn;
	}
	
	
	
	
	
	
	
	
	
	public Long getPerSub() {
		return ( long ) ( ( ( this.getTotalSubSuccess() / ( double ) this.getTotalSub() )*100 )  );
	}
	
	public Long getPerMer() {
		return ( long ) ( ( ( this.getTotalMerSuccess() / ( double ) this.getTotalMer() )*100 )  );
	}
	
	public Long getPerTxn() {
		return  ( long ) ( ( ( this.getTotalTxnSuccess() / ( double ) this.getTotalTxn() )*100 )  );
	}
	
	public Long getPerUser() {
		return ( long ) ( ( ( this.getTotalUserSuccess() / ( double ) this.getTotalUser() )*100 )  );
	}
	

	
	
	
	
	
	
	
	

}
