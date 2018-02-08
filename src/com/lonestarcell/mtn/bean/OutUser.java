package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class OutUser extends AbstractDataBean implements Serializable {
	
	private static final long serialVersionUID = 7431320759077668024L;
	private String username, email, org, userStatus, profile,  lastLogin, date, changePass, userSession;
	private String profileId;
	private String userId;
	
	
	/*
	t.setColumn1( tRaw.getUsername() );
	t.setColumn2( tRaw.getEmail() );
	t.setColumn3( tRaw.getOrganization().getName() );
	t.setColumn4( tRaw.getStatus()+"");
	t.setColumn5( tRaw.getProfile().getProfileName() );
	t.setColumn6( ( tRaw.getLastLogin() != null )?tRaw.getLastLogin().toString():"" );
	t.setDate( (tRaw.getDateAdded() != null )?tRaw.getDateAdded().toString():"" );*/
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.column1 = username;
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.column2 = email;
		this.email = email;
	}
	
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.column3 = org;
		this.org = org;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.column4 = userStatus;
		this.userStatus = userStatus;
	}
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.column5 = profile;
		this.profile = profile;
	}
	
	

	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.column6 = lastLogin;
		this.lastLogin = lastLogin;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getChangePass() {
		return changePass;
	}
	public void setChangePass(String changePass) {
		this.column7 = changePass;
		this.changePass = changePass;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.column8 = userId;
		this.userId = userId;
	}
	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.column9 = profileId + "";
		this.profileId = profileId;
	}
	
	public String getUserSession() {
		return userSession;
	}
	
	public void setUserSession(String userSession) {
		this.column10 = userSession;
		this.userSession = userSession;
	}
	

	
	

}
