package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class ExportUser implements Serializable {
	
	private static final long serialVersionUID = 7431320759077668024L;
	private String username, email, lastLogin, org, userStatus, profile, date;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	

}
