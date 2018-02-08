package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class OutUser implements Serializable {
	
	private static final long serialVersionUID = 7431320759077668024L;
	private String username, email, org, userStatus, profile,  lastLogin, date, changePass, userSession;
	private String profileId;
	private String userId;
	
	
	private String column1;
	private String column2;
	private String column3;
	private String column4;
	private String column5;
	private String column6;
	private String column7;
	private String column8;
	private String column9;
	private String column10;
	private String column11;
	
	
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
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	public String getColumn3() {
		return column3;
	}
	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	public String getColumn4() {
		return column4;
	}
	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	public String getColumn5() {
		return column5;
	}
	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	public String getColumn6() {
		return column6;
	}
	public void setColumn6(String column6) {
		this.column6 = column6;
	}
	public String getColumn7() {
		return column7;
	}
	public void setColumn7(String column7) {
		this.column7 = column7;
	}
	public String getColumn8() {
		return column8;
	}
	public void setColumn8(String column8) {
		this.column8 = column8;
	}
	public String getColumn9() {
		return column9;
	}
	public void setColumn9(String column9) {
		this.column9 = column9;
	}
	public String getColumn10() {
		return column10;
	}
	public void setColumn10(String column10) {
		this.column10 = column10;
	}
	public String getColumn11() {
		return column11;
	}
	public void setColumn11(String column11) {
		this.column11 = column11;
	}
	

	
	

}
