package com.lonestarcell.mtn.bean;

import com.vaadin.data.util.BeanItemContainer;

public class InSettings {
	
	private String username;
	private String userSession;
	private BeanItemContainer< OutProfile > profileContainer;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserSession() {
		return userSession;
	}
	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}
	public BeanItemContainer<OutProfile> getProfileContainer() {
		return profileContainer;
	}
	public void setProfileContainer(BeanItemContainer<OutProfile> profileContainer) {
		this.profileContainer = profileContainer;
	}
	
	
	
	
	
	
	
	
}
