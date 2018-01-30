package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class ExportSubReg implements Serializable {
	
	private static final long serialVersionUID = 7431320759077668024L;
	private String name, msisdn,  idNo, idType, dob, status, regDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getDob() {
		
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	

}
