package com.lonestarcell.mtn.bean;

import java.io.Serializable;

import com.vaadin.data.Property;

public class OutTxnMeta implements Serializable{
	
	private static final long serialVersionUID = -8474942313392257605L;
	private Property< String > totalRecord, totalRevenue;
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
	
	

}
