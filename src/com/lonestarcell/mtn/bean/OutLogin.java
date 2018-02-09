package com.lonestarcell.mtn.bean;

import java.io.Serializable;
import java.util.Date;

import com.vaadin.data.Item;

public class OutLogin implements Serializable{
	
	private static final long serialVersionUID = -5510898681155125171L;
	private String timeCorrection, sessionVar, username, password, status, changePassword;
	private int profileId;
	private Long userId;
	private Date lastLogin;
	private String resetLoginSession;
	
	private Item record;
	
	
	public String getResetLoginSession() {
		return resetLoginSession;
	}
	public void setResetLoginSession(String resetLoginSession) {
		this.resetLoginSession = resetLoginSession;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getSessionVar() {
		return sessionVar;
	}
	public void setSessionVar(String sessionVar) {
		this.sessionVar = sessionVar;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChangePassword() {
		return changePassword;
	}
	public void setChangePassword(String changePassword) {
		this.changePassword = changePassword;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public Item getRecord() {
		return record;
	}
	public void setRecord(Item record) {
		this.record = record;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTimeCorrection() {
		return timeCorrection;
	}
	public void setTimeCorrection(String timeCorrection) {
		this.timeCorrection = timeCorrection;
	}
	
	
	
	
	
	
	
	
	

}
