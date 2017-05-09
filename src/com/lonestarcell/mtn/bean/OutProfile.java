package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class OutProfile implements Serializable{
	
	private static final long serialVersionUID = -8474942313392257605L;
	private String profileName, profileDesc;
	private int profileId;
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileDesc() {
		return profileDesc;
	}
	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	
	
	
	

	
}
