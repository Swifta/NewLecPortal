package com.lonestarcell.mtn.controller.admin;

import com.vaadin.ui.Component;

public interface DUIInitializable {
	void setHeader();

	void setContent();

	void swap(DManUIController duic, Component cuid);

	void init(DManUIController duic);

	void setFooter();

	DManUIController getDUI();

	void setDUI(DManUIController duic);

}
