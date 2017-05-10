package com.lonestarcell.mtn.controller.admin;

import com.vaadin.ui.Component;

public interface DUIInitializable {
	void setHeader();

	void setContent();

	void swap(Component cuid);

	void init();

	void setFooter();

	
}
