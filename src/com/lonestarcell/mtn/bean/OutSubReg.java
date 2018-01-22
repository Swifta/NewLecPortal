package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class OutSubReg extends AbstractDataBean implements Serializable {
	
	private static final long serialVersionUID = 7431320759077668024L;
	private String name, msisdn,  idNo, idType, dob, status, regDate;
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
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.column3 = idNo;
		this.idNo = idNo;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.column4 = idType;
		this.idType = idType;
	}
	public String getDob() {
		
		return dob;
	}
	public void setDob(String dob) {
		this.column5 = dob;
		this.dob = dob;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.column6 = status;
		this.status = status;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.column7 = regDate;
		this.regDate = regDate;
	}
	

}
