package com.lonestarcell.mtn.controller.admin;

import java.util.Set;

import org.springframework.context.ApplicationContext;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public interface ISubUI {
	
	ApplicationContext getSpringAppContext();
	void setSpringAppContext(ApplicationContext springAppContext);
	ISubUI getAncestorUI();
	VerticalLayout getcMainContent();
	void swap( Component cuid );
	void setHeight( String height );
	Set< Short > getPermSet();
	void setPermSet( Set< Short > permSet );

}
