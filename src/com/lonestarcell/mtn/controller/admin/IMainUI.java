package com.lonestarcell.mtn.controller.admin;

import com.vaadin.ui.VerticalLayout;

public interface IMainUI {
	ISubUI getAncestorUI();
	VerticalLayout getcMainContent();
}
