package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class OutUser implements Serializable {
	
	private static final long serialVersionUID = 7431320759077668024L;
	private String date, lastLogin, org, username, email, userStatus, profile, changePass, userSession;
	private int profileId;
	private Long userId;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getChangePass() {
		return changePass;
	}
	public void setChangePass(String changePass) {
		this.changePass = changePass;
	}
	public String getUserSession() {
		return userSession;
	}
	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}
	
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
