package com.lonestarcell.mtn.bean;

import java.io.Serializable;

import com.vaadin.data.util.BeanItemContainer;

public class OutSettings implements Serializable {
	
	private static final long serialVersionUID = 7431320759077668024L;
	
	
	private BeanItemContainer< OutProfile > profileContainer;
	
	public BeanItemContainer<OutProfile> getProfileContainer() {
		return profileContainer;
	}
	public void setProfileContainer(BeanItemContainer<OutProfile> profileContainer) {
		this.profileContainer = profileContainer;
	}
	
	
	
	
	
	

}
