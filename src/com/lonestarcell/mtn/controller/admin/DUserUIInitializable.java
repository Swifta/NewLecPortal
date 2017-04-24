package com.lonestarcell.mtn.controller.admin;

import com.vaadin.ui.Component;

public interface DUserUIInitializable <A,P> {
	void setHeader();

	void setContent();

	void swap( Component cuid );

	void init( A a );

	void setFooter();

	A getAncestorUI();

	void setAncestorUI( A a );
	
	P getParentUI();

	void setParentUI( P p );

}
