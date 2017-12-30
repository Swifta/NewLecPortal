package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class OutConfig implements Serializable{
	
	private static final long serialVersionUID = -8474942313392257605L;
	private String mediatorEPR, coreEPR, timeCorrection;
	private int id;
	public String getMediatorEPR() {
		return mediatorEPR;
	}
	public void setMediatorEPR(String mediatorEPR) {
		this.mediatorEPR = mediatorEPR;
	}
	public String getCoreEPR() {
		return coreEPR;
	}
	public void setCoreEPR(String coreEPR) {
		this.coreEPR = coreEPR;
	}
	public String getTimeCorrection() {
		return timeCorrection;
	}
	public void setTimeCorrection(String timeCorrection) {
		this.timeCorrection = timeCorrection;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

	
}
