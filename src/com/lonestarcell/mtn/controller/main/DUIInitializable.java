package com.lonestarcell.mtn.controller.main;

import com.vaadin.ui.Component;

public interface DUIInitializable {
	void setHeader();
	void setContent();
	void swap(DUI duic, Component cuid);
	void init(DUI duic);
	void setFooter();
	
}
